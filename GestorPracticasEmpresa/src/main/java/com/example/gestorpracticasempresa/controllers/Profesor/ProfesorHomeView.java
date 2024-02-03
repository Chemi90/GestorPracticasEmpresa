package com.example.gestorpracticasempresa.controllers.Profesor;

import com.example.gestorpracticasempresa.Sesion;
import com.example.gestorpracticasempresa.controllers.AlumnoInfo;
import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import com.example.gestorpracticasempresa.domain.Empresa.Empresa;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProfesorHomeView {
    @FXML private Button salirBtn, crearEmpresaBtn, crearAlumnoBtn;
    @FXML private TableView<AlumnoInfo> tbProfesor;
    @FXML private TableColumn<AlumnoInfo, String> cNombreAlumno, cEntrada, cNombreEmpresa;
    @FXML private Label lbNombre;

    public void initialize() {
        // Aquí se configuran las columnas para que usen las propiedades de AlumnoInfo
        cNombreAlumno.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomAlum()));
        cEntrada.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getObserAlum()));
        cNombreEmpresa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomEmpresa()));

        cargarDatosEnTabla();
    }
    private List<AlumnoInfo> obtenerInformacionAlumnos() {
        List<AlumnoInfo> alumnoInfos = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Object[]> resultList = session.createQuery(
                            "SELECT a.dniAlum, a.nomAlum, a.obserAlum FROM Alumno a WHERE a.tutor.idProfesor = :idProfesor", Object[].class)
                    .setParameter("idProfesor", Sesion.getId_profesor().getIdProfesor())
                    .getResultList();
            for (Object[] row : resultList) {
                AlumnoInfo alumnoInfo = new AlumnoInfo((String) row[0], (String) row[1], (String) row[2], null);
                alumnoInfos.add(alumnoInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return alumnoInfos;
    }

    private String obtenerNombreEmpresaPorDniAlumno(String dniAlum) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Empresa empresa = session.createQuery("SELECT a.empresa FROM Alumno a WHERE a.dniAlum = :dniAlum", Empresa.class)
                    .setParameter("dniAlum", dniAlum)
                    .uniqueResult();

            return empresa != null ? empresa.getNomEmpresa() : "N/A";
        } catch (Exception e) {
            e.printStackTrace();
            return "N/A";
        }
    }

    private void cargarDatosEnTabla() {
        List<AlumnoInfo> alumnoInfos = obtenerInformacionAlumnos();
        for (AlumnoInfo alumnoInfo : alumnoInfos) {
            String nomEmpresa = obtenerNombreEmpresaPorDniAlumno(alumnoInfo.getDniAlum());
            System.out.println("Nombre de la empresa para el DNI " + alumnoInfo.getDniAlum() + ": " + nomEmpresa); // Imprimir para debuguear
            alumnoInfo.setNomEmpresa(nomEmpresa);
        }
        ObservableList<AlumnoInfo> datosParaTabla = FXCollections.observableArrayList(alumnoInfos);
        tbProfesor.setItems(datosParaTabla);
    }

    @javafx.fxml.FXML
    public void salir(ActionEvent actionEvent) {
        try {
            // Carga la vista de login
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/login-view.fxml"));
            Parent root = loader.load();

            // Obtiene la ventana (Stage) actual y establece la nueva escena (la pantalla de login)
            Stage stage = (Stage) salirBtn.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

            // Opcional: Si estás manteniendo una sesión, limpia los datos de la sesión aquí
            // Sesion.clear();

        } catch (Exception e) {
            e.printStackTrace();
            // Aquí puedes manejar el error, como mostrar un mensaje de error al usuario.
        }
    }

    @javafx.fxml.FXML
    public void crearEmpresa(ActionEvent actionEvent) {
        try {
            // Asegúrate de que la ruta al archivo FXML es correcta.
            // La ruta debe ser relativa a la carpeta 'src/main/resources' del proyecto.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/crearEmpresa-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) crearEmpresaBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Aquí puedes manejar el error, como mostrar un mensaje de error al usuario.
        }
    }

    @javafx.fxml.FXML
    public void crearAlumno(ActionEvent actionEvent) {
        try {
            // Asegúrate de que la ruta al archivo FXML es correcta.
            // La ruta debe ser relativa a la carpeta 'src/main/resources' del proyecto.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/crearAlumno-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) crearAlumnoBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            // Aquí puedes manejar el error, como mostrar un mensaje de error al usuario.
        }
    }

    public void actualizarBienvenida(String nombreProfesor) {
        lbNombre.setText("Bienvenido " + nombreProfesor);
    }
}
