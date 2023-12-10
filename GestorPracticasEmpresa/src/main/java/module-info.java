module com.example.gestorpracticasempresa {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires lombok;
    requires org.hibernate.orm.core;


    opens com.example.gestorpracticasempresa to javafx.fxml;
    exports com.example.gestorpracticasempresa;
    exports com.example.gestorpracticasempresa.controllers;
    opens com.example.gestorpracticasempresa.controllers to javafx.fxml;
}