package com.example.gestorpracticasempresa.controllers.Alumno;

import com.example.gestorpracticasempresa.Sesion;
import com.example.gestorpracticasempresa.domain.Alumno.AlumnoDAO;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumno;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumnoDAO;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlumnoHomeController
{
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
    private Label nHorasTotales;
    @FXML
    private Label nHorasRestantes;
    @FXML
    private Label nombreTutor;
    @FXML
    private Button salirBtn;
    @FXML
    private Button verEntradasBtn;
    @FXML
    private Button guardarBtn;
    @FXML
    private Label lbNombre;
    @FXML
    private Label nombreEmpresa;
    @FXML
    private ProgressBar progresoHoras;
    @FXML
    private Label lblCreacion;

    @FXML
    public void initialize() {
        actualizarBienvenida(Sesion.getId_alumno().getNomAlum());
        actualizarTutor(Sesion.getId_alumno().getTutor().getNomProf());
        actualizarEmpresa(Sesion.getId_alumno().getEmpresa().getNomEmpresa());
        actualizarHoras(Sesion.getId_alumno().getHorasAct(), Sesion.getId_alumno().getHorasTot());
        tipoPractica.getItems().addAll("dual","clases", "fct");
        tipoPractica.setValue("dual");

        totalHoras.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    totalHoras.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

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
    public void verEntradas(ActionEvent actionEvent) {
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
    public void guardar(ActionEvent actionEvent) {
        LocalDate fechaAlumno = fecha.getValue();
        String tipoPracticaAlumno = tipoPractica.getValue().toString();
        int totalHorasAlumno = Integer.parseInt(totalHoras.getText());
        String actividadAlumno = actividad.getText().toString();
        String observacionesAlumno = observaciones.getText();
        Alumno alumno = Sesion.getId_alumno();
        EntradaAlumnoDAO entradaAlumnoDAO = new EntradaAlumnoDAO(HibernateUtil.getSessionFactory());
        EntradaAlumno entradaAlumno = new EntradaAlumno(fechaAlumno, tipoPracticaAlumno, totalHorasAlumno, actividadAlumno, observacionesAlumno, alumno);
        entradaAlumnoDAO.save(entradaAlumno);
        actualizarHorasAlumno();
        actualizarHoras(Sesion.getId_alumno().getHorasAct(), Sesion.getId_alumno().getHorasTot());
        lblCreacion.setText("La ficha se ha creado con éxito");
        lblCreacion.setVisible(true);
        PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
        visiblePause.setOnFinished(
                event -> lblCreacion.setVisible(false)
        );
        visiblePause.play();

    }

    public void actualizarBienvenida(String nombreAlumno) {
        lbNombre.setText("Bienvenido " + nombreAlumno);
    }

    public void actualizarTutor(String nombreDeTutor){
        nombreTutor.setText("Tutor: " + nombreDeTutor);
    }

    public void actualizarEmpresa(String nombreDeEmpresa) {
        nombreEmpresa.setText("Empresa: " + nombreDeEmpresa);
    }

    public void actualizarHoras(Integer horasAct, Integer horasTot) {
        nHorasRestantes.setText("Horas actuales: " + horasAct.toString());
        nHorasTotales.setText("Horas totales: " + horasTot.toString());

        double progreso = ((double) horasAct /horasTot);
        progresoHoras.setProgress((double) progreso);
    }

    public void actualizarHorasAlumno() {
        AlumnoDAO alumnoDAO = new AlumnoDAO();
        Alumno alumno = Sesion.getId_alumno();
        EntradaAlumnoDAO entradaAlumnoDAO = new EntradaAlumnoDAO(HibernateUtil.getSessionFactory());
        ArrayList<EntradaAlumno> entradas = (ArrayList<EntradaAlumno>) entradaAlumnoDAO.obtenerEntradasPorAlumno(Sesion.getId_alumno().getDniAlum());
        Integer horas = 0;
        for (int i = 0; i < entradas.size(); i++) {
            horas += entradas.get(i).getTotalHoras();
        }
        alumno.setHorasAct(horas);
        try {
            alumnoDAO.update(alumno);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}