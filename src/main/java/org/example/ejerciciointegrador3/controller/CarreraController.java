package org.example.ejerciciointegrador3.controller;

import org.example.ejerciciointegrador3.model.Carrera;
import org.example.ejerciciointegrador3.repositories.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carreras")
public class CarreraController {

    @Qualifier("carreraRepository")
    @Autowired
    private final CarreraRepository carreraRepository;

    public CarreraController(@Qualifier("carreraRepository") CarreraRepository carreraRepository) {
        this.carreraRepository = carreraRepository;
    }


    @RequestMapping("")
    public Iterable<Carrera> obtenerCarreras(){
       return carreraRepository.findAll();
    }

}
