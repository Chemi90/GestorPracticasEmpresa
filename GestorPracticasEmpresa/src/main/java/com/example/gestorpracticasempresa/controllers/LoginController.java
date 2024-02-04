package com.example.gestorpracticasempresa.controllers;

import com.example.gestorpracticasempresa.Sesion;
import com.example.gestorpracticasempresa.controllers.Profesor.ProfesorHomeView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.example.gestorpracticasempresa.domain.Profesor.*;

public class LoginController {
    @javafx.fxml.FXML
    private TextField userField;
    @javafx.fxml.FXML
    private PasswordField passField;
    @javafx.fxml.FXML
    private Button cancelarBtn;
    @javafx.fxml.FXML
    private Button accederBtn;

    @javafx.fxml.FXML
    public void cancelar(ActionEvent actionEvent) {
        // Cierra la aplicación
        Stage stage = (Stage) cancelarBtn.getScene().getWindow();
        stage.close();
    }

    @javafx.fxml.FXML
    public void entrar(ActionEvent actionEvent) {
        String email = userField.getText();
        String contraseña = passField.getText();

        ProfesorDAO profesorDAO = new ProfesorDAO();
        Profesor profesorLogueado = profesorDAO.getByEmailAndPassword(email, contraseña);
        if (profesorLogueado != null) {
            // Guarda el profesor logueado en la sesión
            Sesion.setId_profesor(profesorLogueado);

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/ProfesorHome-view.fxml"));
                Parent root = loader.load();

                // Aquí puedes pasar información al controlador de la vista de inicio si es necesario
                ProfesorHomeView controladorHome = loader.getController();
                controladorHome.actualizarBienvenida(profesorLogueado.getNomProf());

                Stage stage = (Stage) accederBtn.getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de inicio de sesión");
            alert.setHeaderText(null);
            alert.setContentText("Correo electrónico o contraseña incorrectos.");
            alert.showAndWait();
        }
    }
}
