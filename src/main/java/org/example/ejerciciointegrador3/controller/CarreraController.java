package org.example.ejerciciointegrador3.controller;

import org.example.ejerciciointegrador3.dtos.ReporteCarreraDTO;
import org.example.ejerciciointegrador3.model.Carrera;
import org.example.ejerciciointegrador3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController{

    @Autowired
    private CarreraService carreraService;

    @GetMapping
    public ResponseEntity<List<Carrera>> obtenerTodasLasCarreras() {
        List<Carrera> carreras = carreraService.obtenerTodasLasCarreras();
        return ResponseEntity.ok(carreras);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carrera> actualizarCarrera(@PathVariable Long id, @RequestBody Carrera detallesCarrera) {
        Carrera carreraActualizada = carreraService.actualizarCarrera(id, detallesCarrera);
        return ResponseEntity.ok(carreraActualizada);
    }

    @GetMapping("/con-estudiantes")
    public List<Object[]> obtenerCarrerasConEstudiantes() {
        return carreraService.obtenerCarrerasConEstudiantes();
    }

    @GetMapping("/reporte")
    public List<ReporteCarreraDTO> obtenerCarreraReporte(){
        return carreraService.generarReporteCarreras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carrera> obtenerCarreraPorId(@PathVariable Long id) {
        Carrera carrera = carreraService.obtenerCarreraPorId(id);
        return ResponseEntity.ok(carrera);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCarrera(@PathVariable Long id) {
        carreraService.eliminarCarrera(id);
        return ResponseEntity.noContent().build();
    }

}
