package org.example.ejerciciointegrador3.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReporteCarreraDTO {
    private String carrera;
    private Integer anio;
    private Integer graduados;
    private Integer inscriptos;
}