package org.example.ejerciciointegrador3.service;

import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    @Autowired
    private EstudianteRepository estudianteRepository;

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
        return estudianteRepository.findById(id);
    }

}
