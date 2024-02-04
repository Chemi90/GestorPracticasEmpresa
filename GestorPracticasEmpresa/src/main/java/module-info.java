module com.example.gestorpracticasempresa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires jakarta.persistence;

    requires org.hibernate.orm.core;

    requires java.naming;

    // Abre paquetes al FXML de JavaFX y a Hibernate
    opens com.example.gestorpracticasempresa to javafx.fxml;
    exports com.example.gestorpracticasempresa;
    exports com.example.gestorpracticasempresa.controllers;
    opens com.example.gestorpracticasempresa.controllers to javafx.fxml;
    exports com.example.gestorpracticasempresa.controllers.Alumno;
    opens com.example.gestorpracticasempresa.controllers.Alumno to javafx.fxml;
    exports com.example.gestorpracticasempresa.controllers.Empresa;
    opens com.example.gestorpracticasempresa.controllers.Empresa to javafx.fxml;
    exports com.example.gestorpracticasempresa.domain;
    opens com.example.gestorpracticasempresa.domain to javafx.fxml, org.hibernate.orm.core;

    // Abre paquetes de entidad específicos a Hibernate y JavaFX
    opens com.example.gestorpracticasempresa.domain.Alumno to org.hibernate.orm.core, javafx.base;
    opens com.example.gestorpracticasempresa.domain.Empresa to org.hibernate.orm.core, javafx.base;
    opens com.example.gestorpracticasempresa.domain.Profesor to org.hibernate.orm.core, javafx.base;
    exports com.example.gestorpracticasempresa.controllers.Profesor;
    opens com.example.gestorpracticasempresa.controllers.Profesor to javafx.fxml;
    opens com.example.gestorpracticasempresa.domain.EntradaAlumno to org.hibernate.orm.core, javafx.base;
}

