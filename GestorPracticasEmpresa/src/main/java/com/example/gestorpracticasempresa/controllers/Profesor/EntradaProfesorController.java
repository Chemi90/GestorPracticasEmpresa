package com.example.gestorpracticasempresa.controllers.Profesor;

import com.example.gestorpracticasempresa.Sesion;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumno;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumnoDAO;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
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

import java.io.IOException;
import java.util.List;

public class EntradaProfesorController {
    @FXML
    private Button salirBtn, volverBtn;
    @FXML
    private TableView<EntradaAlumno> tbEntradaProfesor;
    @FXML
    private TableColumn<EntradaAlumno, String> cFecha, cTipoPractica, cActividad, cObservaciones;
    @FXML
    private TableColumn<EntradaAlumno, Integer> cHorasDia;
    @FXML
    private Label lbNombre;

    public void initialize() {
        setupTableColumns();
        // Aquí se carga directamente utilizando el DNI del alumno almacenado en la sesión
        String dniAlum = Sesion.getAlumnoInfo().getDniAlum(); // Asegúrate de que este método devuelva el DNI almacenado
        loadEntradasForAlumno(dniAlum);
        tbEntradaProfesor.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Sesion.setEntradaAlumnoSeleccionada(newSelection); // Guarda la selección en la sesión
                try {
                    // Carga la nueva vista
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/modificarEntradaProfesor-view.fxml"));
                    Parent root = loader.load();

                    // Configura el Stage actual con la nueva escena
                    Stage stage = (Stage) tbEntradaProfesor.getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Modificar Entrada");

                    // No es necesario pasar la entrada seleccionada al controlador aquí,
                    // ya que se puede acceder a ella a través de Sesion dentro del controlador de destino
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setupTableColumns() {
        cFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        cTipoPractica.setCellValueFactory(new PropertyValueFactory<>("tipoPractica"));
        cHorasDia.setCellValueFactory(new PropertyValueFactory<>("totalHoras"));
        cActividad.setCellValueFactory(new PropertyValueFactory<>("actividad"));
        cObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
    }

    public void loadEntradasForAlumno(String dniAlum) {
        EntradaAlumnoDAO entradaAlumnoDAO = new EntradaAlumnoDAO(HibernateUtil.getSessionFactory());
        List<EntradaAlumno> entradas = entradaAlumnoDAO.obtenerEntradasPorAlumno(dniAlum);
        ObservableList<EntradaAlumno> datosParaTabla = FXCollections.observableArrayList(entradas);
        tbEntradaProfesor.setItems(datosParaTabla);
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        ((Stage) salirBtn.getScene().getWindow()).close();
    }

    @FXML
    public void volver(ActionEvent actionEvent) {
        try {
            // Carga la vista de profesorHomeView
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/ProfesorHome-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) volverBtn.getScene().getWindow();

            // Cambia la escena del Stage actual
            stage.setScene(new Scene(root));
            stage.setTitle("Vista del Profesor");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
