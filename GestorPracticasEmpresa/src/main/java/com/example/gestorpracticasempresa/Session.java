package com.example.gestorpracticasempresa;

import lombok.Getter;
import lombok.Setter;

public class Session {

    @Setter
    @Getter
    private static Empresa id_empresa; // Usuario actualmente logueado en la sesión
    @Setter
    @Getter
    private static Alumno dni; // Pedido actual en la sesión
    @Setter
    @Getter
    private static Profesor id_profesor;

}
