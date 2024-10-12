package org.example.ejerciciointegrador3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carreraId;
    @Column(nullable = false)
    private String carrera;
    @Column(nullable = false)
    private Integer duracion;

    @OneToMany(mappedBy = "carrera", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<EstudianteCarrera> estudiantes = new ArrayList<>();

    public Carrera(String carrera, Integer duracion) {
        this.carrera = carrera;
        this.duracion = duracion;
    }

    public Carrera() {
        // Constructor por defecto
    }

    public Long getCarreraId() {
        return carreraId;
    }

    public void addEstudiante(EstudianteCarrera estudianteCarrera) {
        this.estudiantes.add(estudianteCarrera);
        estudianteCarrera.setCarrera(this);
    }
}
