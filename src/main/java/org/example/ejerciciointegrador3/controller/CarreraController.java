package org.example.ejerciciointegrador3.controller;

import org.example.ejerciciointegrador3.dtos.ReporteCarreraDTO;
import org.example.ejerciciointegrador3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController{

    @Autowired
    private CarreraService carreraService;

    @GetMapping("/con-estudiantes")
    public List<Object[]> obtenerCarrerasConEstudiantes() {
        return carreraService.obtenerCarrerasConEstudiantes();
    }

    @GetMapping("/reporte")
    public List<ReporteCarreraDTO> obtenerCarreraReporte(){
        return carreraService.generarReporteCarreras();
    }

}
