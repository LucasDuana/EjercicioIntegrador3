package org.example.ejerciciointegrador3.service;

import org.example.ejerciciointegrador3.model.Carrera;
import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.model.EstudianteCarrera;
import org.example.ejerciciointegrador3.repositories.CarreraRepository;
import org.example.ejerciciointegrador3.repositories.EstudianteCarreraRepository;
import org.example.ejerciciointegrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarreraService {

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private EstudianteCarreraRepository estudianteCarreraRepository;

    public void matricularEstudiantePorDni(String dni, Long idCarrera, Integer inscripcion, Integer graduacion, Integer antiguedad) {
        Estudiante estudiante = estudianteRepository.getEstudianteByLu(dni)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado con dni: " + dni));

        Carrera carrera = carreraRepository.findById(idCarrera)
                .orElseThrow(() -> new RuntimeException("Carrera no encontrada con id: " + idCarrera));

        EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
        estudianteCarrera.setEstudiante(estudiante);
        estudianteCarrera.setCarrera(carrera);
        estudianteCarrera.setInscripcion(inscripcion);
        estudianteCarrera.setGraduacion(graduacion);
        estudianteCarrera.setAntiguedad(antiguedad);

        estudianteCarreraRepository.save(estudianteCarrera);
    }

    public List<Object[]> obtenerCarrerasConEstudiantes() {
        return carreraRepository.getCarrerasWithEstudiantesRegisted();
    }

    public Optional<Carrera> getCarreraById(Long id) {
        return carreraRepository.findById(id);
    }

    public List<Object> generarReporteCarreras() {
        return carreraRepository.getReportCarrera();
    }

    public Carrera findById(Long id){
        return carreraRepository.findById(id);
    }
}
