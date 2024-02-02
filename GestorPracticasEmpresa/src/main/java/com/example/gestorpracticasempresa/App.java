package com.example.gestorpracticasempresa;

import com.example.gestorpracticasempresa.domain.Alumno.AlumnoDAO;
import com.example.gestorpracticasempresa.domain.Alumno.AlumnoEntity;
import com.example.gestorpracticasempresa.domain.Empresa.Empresa;
import com.example.gestorpracticasempresa.domain.Profesor.ProfesorEntity;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 900, 800);
        stage.setTitle("Gestor Prácticas de Empresa");
        stage.setScene(scene);
        stage.show();
//        AlumnoDAO alumnoDAO = new AlumnoDAO();
//        Empresa empresa = new Empresa(2, "Telepizza", "123456789", "ManoloPizzerias", "Hace muy buenas pizzas", null, null);
//        Set<Empresa> empresas = new HashSet<>();
//        empresas.add(empresa);
//        ProfesorEntity profesor = new ProfesorEntity(2, "Ibai", "12345", "ibai@hotmail.com", null, empresas);
//        AlumnoEntity alumno = new AlumnoEntity("12312312A", "Xokas",new Date(1706897530) ,"12345", "Xokas@hotmail.com", "123123123", empresa, 10, 100, "Es un experto", profesor, "fct");
//        try {
//            alumnoDAO.remove(alumno);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

    }

    public static void main(String[] args) {
        launch();
    }
}