package com.example.gestorpracticasempresa.controllers.Alumno;

import com.example.gestorpracticasempresa.Sesion;
import com.example.gestorpracticasempresa.controllers.Profesor.ProfesorHomeView;
import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import com.example.gestorpracticasempresa.domain.Alumno.AlumnoDAO;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumno;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumnoDAO;
import com.example.gestorpracticasempresa.domain.HibernateUtil;
import javafx.beans.value.ObservableValue;
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
import javafx.util.Callback;
import org.hibernate.tool.schema.Action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AlumnoTablaController
{
    @FXML
    private Button salirBtn;
    @FXML
    private Button volverBtn;
    @FXML
    private Label lbNombre;
    @FXML
    private TableColumn idCol;
    @FXML
    private TableColumn fechaCol;
    @FXML
    private TableColumn tipoCol;
    @FXML
    private TableColumn horasCol;
    @FXML
    private TableColumn actividadCol;
    @FXML
    private TableColumn obvCol;
    @FXML
    private TableView tablaView;
    @FXML
    private Button editarBtn;
    @FXML
    private Button borrarBtn;

    @FXML
    public void initialize() {
        actualizarBienvenida(Sesion.getId_alumno().getNomAlum());
        EntradaAlumnoDAO entradaAlumnoDAO = new EntradaAlumnoDAO(HibernateUtil.getSessionFactory());
        ArrayList<EntradaAlumno> entradas = (ArrayList<EntradaAlumno>) entradaAlumnoDAO.obtenerEntradasPorAlumno(Sesion.getId_alumno().getDniAlum());
        ObservableList<EntradaAlumno> entradasObv = FXCollections.observableArrayList(entradas);

        tablaView.setItems(entradasObv);
        tablaView.getSelectionModel().clearAndSelect(0);
        //La columna de id se crea y se esconde solamente para poder obtener las entradas de la dbb cuando el usuario quiere editar o borrar una en especifico
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        idCol.setVisible(false);
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tipoCol.setCellValueFactory(new PropertyValueFactory<>("tipoPractica"));
        horasCol.setCellValueFactory(new PropertyValueFactory<>("totalHoras"));
        actividadCol.setCellValueFactory(new PropertyValueFactory<>("actividad"));
        obvCol.setCellValueFactory(new PropertyValueFactory<>("observaciones"));

        actualizarHorasAlumno();


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

    public void editarButton(ActionEvent actionEvent) {
        try {
            // Carga la vista de editar entrada
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/gestorpracticasempresa/alumnoEditEntrada-view.fxml"));
            Parent root = loader.load();
            AlumnoEditEntradaController controller = loader.getController();

            EntradaAlumno entradaAlumno = (EntradaAlumno) tablaView.getSelectionModel().getSelectedItem();
            controller.setEntrada(entradaAlumno);

            Stage stage = (Stage) volverBtn.getScene().getWindow();

            // Cambia la escena del Stage actual
            stage.setScene(new Scene(root));
            stage.setTitle("Editar una entrada");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void borrarButton(ActionEvent actionEvent) {
        EntradaAlumno entradaAlumno = (EntradaAlumno) tablaView.getSelectionModel().getSelectedItem();
        if (Objects.nonNull(entradaAlumno)) {
            EntradaAlumnoDAO entradaAlumnoDAO = new EntradaAlumnoDAO(HibernateUtil.getSessionFactory());
            int index = tablaView.getSelectionModel().getSelectedIndex();
            Object item = tablaView.getItems().get(index);
            TableColumn colId = (TableColumn) tablaView.getColumns().get(0);
            String id = colId.getCellObservableValue(item).getValue().toString();
            entradaAlumnoDAO.get(Long.valueOf(id));
            entradaAlumnoDAO.remove(entradaAlumno);
            ObservableList<EntradaAlumno> selectedRows = tablaView.getSelectionModel().getSelectedItems();
            ArrayList<EntradaAlumno> rows = new ArrayList<>(selectedRows);
            rows.forEach(row -> tablaView.getItems().remove(row));
            actualizarHorasAlumno();
        }else {

        }

    }

    public void actualizarBienvenida(String nombreAlumno) {
        lbNombre.setText("Bienvenido " + nombreAlumno);
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