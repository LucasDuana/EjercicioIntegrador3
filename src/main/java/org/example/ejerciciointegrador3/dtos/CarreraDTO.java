package org.example.ejerciciointegrador3.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CarreraDTO {

    @NotBlank(message = "El nombre de la carrera es obligatorio")
    @Size(max = 100, message = "El nombre de la carrera no puede exceder los 100 caracteres")
    private String carrera;

    @NotNull(message = "La duración es obligatoria")
    private Integer duracion;

    // Getters y Setters
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
}
