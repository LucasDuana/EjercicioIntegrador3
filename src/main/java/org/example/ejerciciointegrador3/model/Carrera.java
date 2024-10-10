package org.example.ejerciciointegrador3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrera_id;
    @Column
    private String carrera;
    @Column
    private Integer duracion;

    @OneToMany(mappedBy = "carrera")
    private List<EstudianteCarrera> estudiantes;

    public Carrera(String carrera, Integer duracion) {
        this.carrera = carrera;
        this.duracion = duracion;
        this.estudiantes = new ArrayList<>();
    }

    public Carrera() {

    }

    public Long getId_carrera() {
        return carrera_id;
    }


    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public List<EstudianteCarrera> getEstudiantes() {
        return new ArrayList<>(estudiantes);
    }

    public void addEstudiante(EstudianteCarrera e){
        this.estudiantes.add(e);
    }
}
