package org.example.ejerciciointegrador3.service;

import jakarta.transaction.Transactional;
import org.example.ejerciciointegrador3.model.Carrera;
import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.model.EstudianteCarrera;
import org.example.ejerciciointegrador3.repositories.EstudianteCarreraRepository;
import org.example.ejerciciointegrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    public List<Estudiante> obtenerTodosEstudiantes() {
        try {
            return estudianteRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener todos los estudiantes", e);
        }
    }

    public Estudiante crearEstudiante(Estudiante entity) {
        try {
            return estudianteRepository.save(entity);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear estudiante", e);
        }
    }

    public List<Estudiante> obtenerEstudiantesPorGenero(String genero) {
        try {
            return estudianteRepository.getEstudiantesByGenero(genero);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener estudiantes por género", e);
        }
    }

    public Optional<Estudiante> obtenerEstudiantePorLU(String lu) {
        try {
            return estudianteRepository.getEstudianteByLu(lu);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener estudiante por LU", e);
        }
    }

    public List<Estudiante> obtenerEstudiantesOrdenadosPor(String campoOrden) {
        try {
            return estudianteRepository.findAll(Sort.by(Sort.Direction.ASC, campoOrden));
        } catch (Exception e) {
            throw new RuntimeException("Error al ordenar estudiantes por " + campoOrden, e);
        }
    }

    public Estudiante findById(Long id) {
        return estudianteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con ID: " + id));
    }



    @Transactional
    public void matricularEstudiante(Estudiante estudiante, Carrera carrera) {
        try {
            EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
            estudianteCarrera.setEstudiante(estudiante);
            estudianteCarrera.setCarrera(carrera);
            estudianteCarrera.setInscripcion(LocalDate.now().getYear());
            estudianteCarreraRepository.save(estudianteCarrera);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo matricular al alumno",e);
        }
    }

    public List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(Long carreraId, String ciudad) {
        try {
            return estudianteRepository.findEstudiantesByCarreraAndCiudad(carreraId, ciudad);
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener estudiantes por carrera y ciudad", e);
        }
    }

}
