module com.example.gestorpracticasempresa {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;


    opens com.example.gestorpracticasempresa to javafx.fxml;
    exports com.example.gestorpracticasempresa;
    exports com.example.gestorpracticasempresa.controllers;
    opens com.example.gestorpracticasempresa.controllers to javafx.fxml;
    exports com.example.gestorpracticasempresa.persistence;
    opens com.example.gestorpracticasempresa.persistence to javafx.fxml;
    exports com.example.gestorpracticasempresa.controllers.Alumno;
    opens com.example.gestorpracticasempresa.controllers.Alumno to javafx.fxml;
    exports com.example.gestorpracticasempresa.controllers.Empresa;
    opens com.example.gestorpracticasempresa.controllers.Empresa to javafx.fxml;
}