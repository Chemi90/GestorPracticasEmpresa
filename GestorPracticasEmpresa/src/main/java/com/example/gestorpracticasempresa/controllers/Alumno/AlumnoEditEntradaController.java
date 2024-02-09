package com.example.gestorpracticasempresa.controllers.Alumno;

import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumno;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumnoDAO;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.SessionFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AlumnoEditEntradaController {
    @FXML
    private DatePicker fecha;
    @FXML
    private ChoiceBox tipoPractica;
    @FXML
    private TextField totalHoras;
    @FXML
    private TextField actividad;
    @FXML
    private TextArea observaciones;
    @FXML
    private Button salirBtn;
    @FXML
    private Button verEntradasBtn;
    @FXML
    private Button guardarBtn;
    private EntradaAlumno entradaAlumno;


    @FXML
    public void initialize(){
    }


    @FXML
    public void salir (ActionEvent actionEvent) {
        try {
            Stage stage = (Stage) salirBtn.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void verEntradas (ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/alumnoTabla-view.fxml"));
            Parent root = loader.load();

            // Obtiene el Stage actual
            Stage stage = (Stage) salirBtn.getScene().getWindow();
            // Cambia la escena del Stage actual
            stage.setScene(new Scene(root));
            stage.setTitle("Entradas");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void guardar (ActionEvent actionEvent) {
        EntradaAlumnoDAO entradaAlumnoDAO = new EntradaAlumnoDAO(HibernateUtil.getSessionFactory());
        entradaAlumno.setActividad(actividad.getText());
        entradaAlumno.setFecha(fecha.getValue());
        entradaAlumno.setObservaciones(observaciones.getText());
        entradaAlumno.setTipoPractica((String) tipoPractica.getValue());
        entradaAlumno.setTotalHoras(Integer.parseInt(totalHoras.getText()));
        entradaAlumno.setActividad(actividad.getText());
        entradaAlumnoDAO.update(entradaAlumno);
        verEntradasBtn.fire();
    }

    public void setEntrada (EntradaAlumno entradaAlumno) {
        this.entradaAlumno = entradaAlumno;

        totalHoras.setText(String.valueOf(entradaAlumno.getTotalHoras()));
        fecha.setValue(entradaAlumno.getFecha());
        tipoPractica.getItems().addAll("dual","clases", "fct");
        tipoPractica.setValue(entradaAlumno.getTipoPractica());
        actividad.setText(entradaAlumno.getActividad());
        observaciones.setText(entradaAlumno.getObservaciones());
    }

}
