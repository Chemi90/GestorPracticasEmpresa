package com.example.gestorpracticasempresa.domain.EntradaAlumno;

import com.example.gestorpracticasempresa.domain.Alumno.Alumno;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "EntradaPractica_temp")
public class EntradaAlumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entrada") // Asegúrate de que el nombre de la columna aquí coincida con el de tu tabla
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "tipo_practica")
    private String tipoPractica;

    @Column(name = "total_horas")
    private int totalHoras;

    @Column(name = "actividad")
    private String actividad;

    @Column(name = "observaciones")
    private String observaciones;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni_alum", referencedColumnName = "dni_alum")
    @ToString.Exclude
    private Alumno alumno;

    public EntradaAlumno(LocalDate fecha, String tipoPractica, int totalHoras, String actividad, String observaciones, Alumno alumno) {
        this.fecha = fecha;
        this.tipoPractica = tipoPractica;
        this.totalHoras = totalHoras;
        this.actividad = actividad;
        this.observaciones = observaciones;
        this.alumno = alumno;
    }

    // Getters y setters
}
