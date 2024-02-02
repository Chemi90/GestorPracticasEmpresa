package com.example.gestorpracticasempresa;

import com.example.gestorpracticasempresa.domain.Alumno.AlumnoEntity;
import com.example.gestorpracticasempresa.domain.Empresa.Empresa;
import com.example.gestorpracticasempresa.domain.Profesor.ProfesorEntity;
import lombok.Getter;
import lombok.Setter;

public class Session {

    @Setter
    @Getter
    private static Empresa id_empresa; // Usuario actualmente logueado en la sesión
    @Setter
    @Getter
    private static AlumnoEntity dni; // Pedido actual en la sesión
    @Setter
    @Getter
    private static ProfesorEntity id_profesor;

}
