package com.example.gestorpracticasempresa;

import com.example.gestorpracticasempresa.controllers.AlumnoInfo;
import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import com.example.gestorpracticasempresa.domain.Empresa.Empresa;
import com.example.gestorpracticasempresa.domain.EntradaAlumno.EntradaAlumno;
import com.example.gestorpracticasempresa.domain.Profesor.Profesor;
import lombok.Getter;
import lombok.Setter;

public class Sesion {

    @Setter
    @Getter
    private static Empresa id_empresa; // Usuario actualmente logueado en la sesión
    @Setter
    @Getter
    private static Alumno dni; // Pedido actual en la sesión
    @Setter
    @Getter
    private static Profesor id_profesor;
    @Setter
    @Getter
    private static AlumnoInfo alumnoInfo;
    @Setter @Getter
    private static EntradaAlumno entradaAlumnoSeleccionada;

}
