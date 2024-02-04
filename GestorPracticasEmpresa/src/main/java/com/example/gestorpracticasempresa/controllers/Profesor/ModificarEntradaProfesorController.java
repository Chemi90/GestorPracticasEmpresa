package com.example.gestorpracticasempresa.controllers.Profesor;

import com.example.gestorpracticasempresa.Sesion;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumno;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.hibernate.Session;

import java.io.IOException;
import java.time.LocalDate;

public class ModificarEntradaProfesorController {
    @javafx.fxml.FXML
    private ImageView icnPersona;
    @javafx.fxml.FXML
    private Button btnSalir;
    @javafx.fxml.FXML
    private ImageView icnPersona2;
    @javafx.fxml.FXML
    private TextField TFObservaciones;
    @javafx.fxml.FXML
    private Button btnCancelar;
    @javafx.fxml.FXML
    private Button btnGuardar;
    @javafx.fxml.FXML
    private DatePicker dtFecha;
    @javafx.fxml.FXML
    private ChoiceBox cbPractica;
    @javafx.fxml.FXML
    private TextField tfHorasDia;
    @javafx.fxml.FXML
    private TextField tfActividad;

    @FXML
    public void initialize() {
        cbPractica.getItems().addAll("FCT", "DUAL");
        cargarDatosDesdeSesion();
    }


    public void cargarDatos(EntradaAlumno entradaSeleccionada) {
        if (entradaSeleccionada != null) {
            dtFecha.setValue(entradaSeleccionada.getFecha());
            cbPractica.setValue(entradaSeleccionada.getTipoPractica());
            tfHorasDia.setText(String.valueOf(entradaSeleccionada.getTotalHoras()));
            tfActividad.setText(entradaSeleccionada.getActividad());
            TFObservaciones.setText(entradaSeleccionada.getObservaciones());
        }
    }

    @FXML
    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void cerrar(ActionEvent actionEvent) {
        // Cerrar la ventana
        ((Button)actionEvent.getSource()).getScene().getWindow().hide();
    }

    @FXML
    public void guardarCambios(ActionEvent actionEvent) {
        // Asumiendo que tienes una instancia de EntradaAlumno que estás editando
        EntradaAlumno entrada = Sesion.getEntradaAlumnoSeleccionada();
        if (entrada != null) {
            // Actualiza los datos de la entrada con los valores de los campos de entrada
            entrada.setFecha(dtFecha.getValue());
            entrada.setTipoPractica(cbPractica.getSelectionModel().getSelectedItem().toString());
            entrada.setTotalHoras(Integer.parseInt(tfHorasDia.getText()));
            entrada.setActividad(tfActividad.getText());
            entrada.setObservaciones(TFObservaciones.getText());

            // Aquí deberías llamar al método que persiste los cambios en la base de datos
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                session.beginTransaction();
                session.update(entrada);
                session.getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
                // Manejo de errores, por ejemplo, mostrar un diálogo de error al usuario
            }

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/entradasProfesor-view.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage currentStage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
                currentStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
                // Manejar la excepción
            }
        } else {
            // Manejar el caso en que no hay una entrada seleccionada para editar
            // Por ejemplo, mostrando un mensaje de error al usuario
        }
    }

    public void cargarDatosDesdeSesion() {
        EntradaAlumno entradaSeleccionada = Sesion.getEntradaAlumnoSeleccionada();
        if (entradaSeleccionada != null) {
            cargarDatos(entradaSeleccionada);
        }
    }
}

