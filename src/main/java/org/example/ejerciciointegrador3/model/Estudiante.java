package org.example.ejerciciointegrador3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


    @Entity
    @Data
    public class Estudiante {
        @Id
        private Long dni;
        @Column
        private String nombre;
        @Column
        private String apellido;
        @Column
        private Integer edad;
        @Column
        private String genero;
        @Column
        private String ciudad;
        @Column
        private String lu;

        @OneToMany(mappedBy = "estudiante")
        @JsonIgnore
        private List<EstudianteCarrera> carreras;

        public Estudiante() {
            this.carreras = new ArrayList<>();
        }

        public Estudiante(Long dni, String nombre, String apellido, Integer edad, String genero, String ciudad, String lu) {
            this.dni = dni;
            this.nombre = nombre;
            this.apellido = apellido;
            this.edad = edad;
            this.genero = genero;
            this.ciudad = ciudad;
            this.lu = lu;
            this.carreras = new ArrayList<>();
        }

        public void addCareraa(EstudianteCarrera c){
            this.carreras.add(c);
        }

        public List<EstudianteCarrera> getCarreras(){
            return new ArrayList<>(carreras);
        }






    }


