package com.example.gestorpracticasempresa.controllers.Profesor;

import com.example.gestorpracticasempresa.Sesion;
import com.example.gestorpracticasempresa.controllers.AlumnoInfo;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorHomeView {
    @FXML private Button salirBtn, crearEmpresaBtn, crearAlumnoBtn;
    @FXML private TableView<AlumnoInfo> tbProfesor;
    @FXML private TableColumn<AlumnoInfo, String> cNombreAlumno, cEntrada, cNombreEmpresa;
    @FXML private Label lbNombre;

    public void initialize() {
        configureTableColumns();
        loadTableData();
        setupTableSelectionListener();
    }

    private void configureTableColumns() {
        cNombreAlumno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomAlum()));
        cEntrada.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObserAlum()));
        cNombreEmpresa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomEmpresa()));
    }

    private void loadTableData() {
        ObservableList<AlumnoInfo> alumnoInfos = FXCollections.observableArrayList(obtainAlumnosInfo());
        tbProfesor.setItems(alumnoInfos);
    }

    private List<AlumnoInfo> obtainAlumnosInfo() {
        List<AlumnoInfo> alumnoInfos = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Object[]> resultList = session.createQuery(
                            "SELECT a.dniAlum, a.nomAlum, a.obserAlum, e.nomEmpresa FROM Alumno a LEFT JOIN a.empresa e WHERE a.tutor.idProfesor = :idProfesor", Object[].class)
                    .setParameter("idProfesor", Sesion.getId_profesor().getIdProfesor())
                    .getResultList();
            for (Object[] row : resultList) {
                String nomEmpresa = row[3] != null ? (String) row[3] : "N/A";
                alumnoInfos.add(new AlumnoInfo((String) row[0], (String) row[1], (String) row[2], nomEmpresa));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumnoInfos;
    }

    private void setupTableSelectionListener() {
        tbProfesor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                AlumnoInfo selectedAlumnoInfo = newSelection;
                Sesion.setAlumnoInfo(selectedAlumnoInfo); // Adjust Sesion to store AlumnoInfo
                openEntradaProfesorView();
            }
        });
    }

    private void openEntradaProfesorView() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/entradasProfesor-view.fxml"));
            Parent root = loader.load();

            // Obtiene el Stage actual
            Stage stage = (Stage) tbProfesor.getScene().getWindow();
            // Cambia la escena del Stage actual
            stage.setScene(new Scene(root));
            stage.setTitle("Entradas del Alumno");
            // No necesitas mostrarlo ya que simplemente estás cambiando la escena del Stage existente
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) salirBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void crearEmpresa(ActionEvent actionEvent) {
        loadView("/com/example/gestorpracticasempresa/crearEmpresa-view.fxml");
    }

    @FXML
    public void crearAlumno(ActionEvent actionEvent) {
        loadView("/com/example/gestorpracticasempresa/crearAlumno-view.fxml");
    }

    private void loadView(String fxmlPath) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxmlPath));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarBienvenida(String nombreProfesor) {
        lbNombre.setText("Bienvenido " + nombreProfesor);
    }
}
