module com.example.gestorpracticasempresa {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gestorpracticasempresa to javafx.fxml;
    exports com.example.gestorpracticasempresa;
}