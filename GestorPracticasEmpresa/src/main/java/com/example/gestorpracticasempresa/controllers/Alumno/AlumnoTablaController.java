package com.example.gestorpracticasempresa.controllers.Alumno;

import com.example.gestorpracticasempresa.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AlumnoTablaController
{
    @FXML
    private Button salirBtn;
    @FXML
    private Button volverBtn;
    @FXML
    private Label lbNombre;

    @FXML
    public void initialize() {
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        actualizarBienvenida(Sesion.getId_alumno().getNomAlum());
    }

    @FXML
    public void volver(ActionEvent actionEvent) {
        try {
            // Carga la vista de alumnoHomeView
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/AlumnoHome-view.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) volverBtn.getScene().getWindow();

            // Cambia la escena del Stage actual
            stage.setScene(new Scene(root));
            stage.setTitle("Vista del Alumno");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actualizarBienvenida(String nombreAlumno) {
        lbNombre.setText("Bienvenido " + nombreAlumno);
    }
}