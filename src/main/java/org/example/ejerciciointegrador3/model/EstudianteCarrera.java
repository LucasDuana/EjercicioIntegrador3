package org.example.ejerciciointegrador3.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Entity
@Data
public class EstudianteCarrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "dni")
    private Estudiante estudiante;

    @Setter
    @ManyToOne
    @JoinColumn(name = "carrera_id")
    private Carrera carrera;

    private Date inscripcion;

    private Integer graduacion;

    private Integer antiguedad;


    public EstudianteCarrera(Long id,Carrera carrera, Estudiante estudiante, Date inscripcion, Integer graduacion, Integer antiguedad) {
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.inscripcion = inscripcion;
        this.antiguedad = antiguedad;
        this.graduacion = graduacion;


    }

    public EstudianteCarrera() {

    }


    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }

    public void setInscripcion(Date inscripcion) {
        this.inscripcion = inscripcion;
    }

    public void setGraduacion(Integer graduacion) {
        this.graduacion = graduacion;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }
}
