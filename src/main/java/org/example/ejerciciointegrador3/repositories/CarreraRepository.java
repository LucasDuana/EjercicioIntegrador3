package org.example.ejerciciointegrador3.repositories;

import org.example.ejerciciointegrador3.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {
    /*
    @Query("SELECT c, COUNT(ec) FROM Carrera c JOIN EstudianteCarrera ec ON c.carreraId = ec.carrera.carreraId " +
            "GROUP BY c ORDER BY COUNT(ec) DESC")
    public List<Carrera> getCarrerasWithEstudiantesRegisted();*/

    @Query("SELECT c, COUNT(ec) FROM Carrera c " +
            "JOIN EstudianteCarrera ec ON c.carreraId = ec.carrera.carreraId " +
            "GROUP BY c ORDER BY COUNT(ec) DESC")
    List<Object[]> getCarrerasWithEstudiantesRegisted();

    @Query("SELECT c.carrera, ec.inscripcion, ec.graduacion " +
            "FROM EstudianteCarrera ec " +
            "JOIN ec.carrera c " +
            "ORDER BY c.carrera ASC, ec.inscripcion ASC")
    List<Object[]> obtenerDatosCarreras();






}
