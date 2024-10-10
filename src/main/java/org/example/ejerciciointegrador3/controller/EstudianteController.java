package org.example.ejerciciointegrador3.controller;

import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudiantes")
public class EstudianteController{

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("")
    public ResponseEntity<List<Estudiante>> obtenerEstudiantes(){
        List<Estudiante> estudiantes= estudianteService.obtenerTodosEstudiantes();
        if(estudiantes.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(estudiantes);
    }


    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody Estudiante estudiante){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(estudianteService.crearEstudiante(estudiante));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al registrar un nuevo estudiante");
        }
    }

}
