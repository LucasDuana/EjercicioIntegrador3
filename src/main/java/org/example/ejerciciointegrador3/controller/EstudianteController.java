package org.example.ejerciciointegrador3.controller;

import org.example.ejerciciointegrador3.model.Carrera;
import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.service.CarreraService;
import org.example.ejerciciointegrador3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController {

    @Autowired
    private EstudianteService estudianteService;




    @Autowired
    private CarreraService carreraService;

    @GetMapping("")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.obtenerTodosEstudiantes();
        if (estudiantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    //a) dar de alta un estudiante
    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante) {
        try {
            Estudiante nuevoEstudiante = estudianteService.crearEstudiante(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar un nuevo estudiante: " + e.getMessage());
        }
    }

    //d) recuperar un estudiante, en base a su número de libreta universitaria.
    @GetMapping("/libreta/{lu}")
    public ResponseEntity<Estudiante> obtenerEstudiantePorLU(@PathVariable String lu) {
        Optional<Estudiante> estudiante = estudianteService.obtenerEstudiantePorLU(lu);
        return estudiante.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    //e) recuperar todos los estudiantes, en base a su género.
    @GetMapping("/genero")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorGenero(@RequestParam String genero) {
        List<Estudiante> estudiantes = estudianteService.obtenerEstudiantesPorGenero(genero);
        if (estudiantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(estudiantes);
    }

    //c) recuperar todos los estudiantes, y especificar algún criterio de ordenamiento simp
    @GetMapping("/ordenados")
    public List<Estudiante> obtenerEstudiantesOrdenados(@RequestParam(defaultValue = "nombre") String campoOrden) {
        return estudianteService.obtenerEstudiantesOrdenadosPor(campoOrden);
    }

    //b matricular estudiante
    @PostMapping("/{estudianteId}/carrera/{carreraId}")
    public ResponseEntity<String> matricularEstudiante(@PathVariable Long estudianteId, @PathVariable Long carreraId) {
        // Recuperar el estudiante y la carrera
        Estudiante estudiante = estudianteService.findById(estudianteId);
        Carrera carrera = carreraService.findById(carreraId);

        // Validar que ambos existan
        if (estudiante == null || carrera == null) {
            return ResponseEntity.badRequest().body("Estudiante o carrera no encontrados");
        }

        estudianteService.matricularEstudiante(estudiante,carrera);

        return ResponseEntity.ok("Estudiante matriculado con éxito");
    }

    //g) recuperar los estudiantes de una determinada carrera, filtrado por ciudad de residencia.
    @GetMapping("/carrera/{carreraId}/ciudad/{ciudad}")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantesPorCarreraYCiudad(
            @PathVariable Long carreraId, @PathVariable String ciudad) {
        List<Estudiante> estudiantes = estudianteService.obtenerEstudiantesPorCarreraYCiudad(carreraId, ciudad);
        if (estudiantes.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Devolver 404 si no hay estudiantes
        }

        return ResponseEntity.ok(estudiantes);
    }




}
