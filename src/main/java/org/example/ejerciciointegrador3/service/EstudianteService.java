package org.example.ejerciciointegrador3.service;

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
        return estudianteRepository.findAll();
    }

    public Estudiante crearEstudiante(Estudiante entity) {
        return estudianteRepository.save(entity);
    }

    public List<Estudiante> obtenerEstudiantesPorGenero(String genero) {
        return estudianteRepository.getEstudiantesByGenero(genero);
    }

    public Optional<Estudiante> obtenerEstudiantePorLU(String lu) {
        return estudianteRepository.getEstudianteByLu(lu);
    }

    public List<Estudiante> obtenerEstudiantesOrdenadosPor(String campoOrden) {
        return estudianteRepository.findAll(Sort.by(Sort.Direction.ASC, campoOrden));
    }

    public Estudiante findById(Long id){
        return estudianteRepository.findById(id).orElseThrow(null);
    }

    public String matricularEstudiante(Estudiante estudiante,Carrera carrera) {


        EstudianteCarrera estudianteCarrera = new EstudianteCarrera();
        estudianteCarrera.setEstudiante(estudiante);
        estudianteCarrera.setCarrera(carrera);


        estudianteCarreraRepository.save(estudianteCarrera);

        return "Estudiante matriculado con éxito";
    }


    public List<Estudiante> obtenerEstudiantesPorCarreraYCiudad(Long carreraId, String ciudad) {
        return estudianteRepository.findEstudiantesByCarreraAndCiudad(carreraId, ciudad);
    }

}
