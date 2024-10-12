package org.example.ejerciciointegrador3.controller;

import org.example.ejerciciointegrador3.model.Carrera;
import org.example.ejerciciointegrador3.model.EstudianteCarrera;
import org.example.ejerciciointegrador3.repositories.EstudianteCarreraRepository;
import org.springframework.data.domain.Sort;
import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.service.CarreraService;
import org.example.ejerciciointegrador3.repositories.EstudianteRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    private final CarreraService carreraService;
    private final EstudianteRepository estudianteRepository;
    private final EstudianteCarreraRepository estudianteCarreraRepository;


    public CarreraController(CarreraService carreraService, EstudianteRepository estudianteRepository, EstudianteCarreraRepository estudianteCarreraRepository) {
        this.carreraService = carreraService;
        this.estudianteRepository = estudianteRepository;
        this.estudianteCarreraRepository = estudianteCarreraRepository;
    }

    @PostMapping("/matricular/{dni}/{idCarrera}")
    public ResponseEntity<String> matricularEstudiante(
            @PathVariable Long dni,
            @PathVariable Long idCarrera) {
        try {

            Estudiante estudiante = estudianteRepository.getEstudianteByDni(dni)
                    .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));


            Carrera carrera = carreraService.getCarreraById(idCarrera)
                    .orElseThrow(() -> new RuntimeException("Carrera no encontrada"));


            EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
            estudianteCarrera.setEstudiante(estudiante);
            estudianteCarrera.setCarrera(carrera);
            estudianteCarrera.setInscripcion(java.time.Year.now().getValue());
            estudianteCarrera.setGraduacion(estudianteCarrera.getInscripcion() + carrera.getDuracion());
            estudianteCarrera.setAntiguedad(0);


            estudianteCarreraRepository.save(estudianteCarrera);

            return ResponseEntity.status(HttpStatus.OK).body("Estudiante matriculado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al matricular: " + e.getMessage());
        }
    }



    @GetMapping("/reporte-carreras")
    public ResponseEntity<List<Object>> generarReporteCarreras() {
        try {
            List<Object> reporte = Collections.singletonList(carreraService.generarReporteCarreras());
            return ResponseEntity.ok(reporte);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/estudiantes")
    public ResponseEntity<String> altaEstudiante(@RequestBody Estudiante estudiante) {
        try {
            estudianteRepository.save(estudiante);
            return ResponseEntity.status(HttpStatus.CREATED).body("Estudiante creado exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al crear estudiante");
        }
    }

    @GetMapping("/estudiantes")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantes(@RequestParam(defaultValue = "nombre") String orden) {
        List<Estudiante> estudiantes = estudianteRepository.findAll(Sort.by(orden));
        return ResponseEntity.ok(estudiantes);
    }
}
