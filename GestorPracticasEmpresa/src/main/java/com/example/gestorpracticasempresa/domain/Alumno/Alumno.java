package com.example.gestorpracticasempresa.domain.Alumno;

import com.example.gestorpracticasempresa.domain.Empresa.Empresa;
import com.example.gestorpracticasempresa.domain.Profesor.Profesor;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Alumno")
public class Alumno {
    @Id
    @Column(name = "dni_alum")
    private String dniAlum;

    @Column(name = "nom_alum", nullable = false)
    private String nomAlum;

    @Column(name = "fecha_nac", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaNac;

    @Column(name = "contra_alum", nullable = false)
    private String contraAlum;

    @Column(name = "email_alum", nullable = false)
    private String emailAlum;

    @Column(name = "telef_alum", nullable = false)
    private String telefAlum;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa empresa;

    @Column(name = "horas_act")
    private Integer horasAct;

    @Column(name = "horas_tot")
    private Integer horasTot;

    @Column(name = "obser_alum")
    private String obserAlum;

    @ManyToOne
    @JoinColumn(name = "id_tutor")
    private Profesor tutor;

    @Column(name = "tipo_alum")
    private String tipoAlum;

    private static final Set<String> TIPOS_PERMITIDOS = Set.of("dual", "fct", "clases");

    public void setTipoAlum(String tipoAlum) {
        if (TIPOS_PERMITIDOS.contains(tipoAlum.toLowerCase())) {
            this.tipoAlum = tipoAlum;
        } else {
            throw new IllegalArgumentException("Valor no permitido para tipoAlum. Los valores permitidos son: dual, fct, clases.");
        }
    }

}



