package org.example.ejerciciointegrador3.service;

import jakarta.transaction.Transactional;
import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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



}
