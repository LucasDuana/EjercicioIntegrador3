package org.example.ejerciciointegrador3.controller;

import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/estudiantes")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.obtenerTodosEstudiantes();
        if (estudiantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    @PostMapping("/estudiantes")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevoEstudiante = estudianteService.crearEstudiante(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar un nuevo estudiante: " + e.getMessage());
        }
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorGenero(@PathVariable String genero) {
        List<Estudiante> estudiantes = estudianteService.obtenerEstudiantesPorGenero(genero);
        if (estudiantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/libreta/{lu}")
    public ResponseEntity<Estudiante> obtenerEstudiantePorLU(@PathVariable String lu) {
        Optional<Estudiante> estudiante = estudianteService.obtenerEstudiantePorLU(lu);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
