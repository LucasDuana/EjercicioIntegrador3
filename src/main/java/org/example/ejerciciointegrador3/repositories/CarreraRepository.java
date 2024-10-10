package org.example.ejerciciointegrador3.repositories;

import org.example.ejerciciointegrador3.model.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Long> {

    @Query("SELECT c, COUNT(ec) FROM Carrera c JOIN EstudianteCarrera ec ON c.carrera_id = ec.carrera.carrera_id " +
            "GROUP BY c ORDER BY COUNT(ec) DESC")
    public List<Carrera> getCarrerasWithEstudiantesRegisted();

    @Query("SELECT c.carrera, ec.inscripcion AS anio, " +
            "SUM(CASE WHEN ec.graduacion = 0 THEN 1 ELSE 0 END) AS graduados, " +
            "COUNT(CASE WHEN ec.graduacion > 0 THEN 1 END) AS inscriptos " +
            "FROM EstudianteCarrera ec " +
            "JOIN ec.carrera c " +
            "WHERE ec.inscripcion IS NOT NULL " +
            "GROUP BY c.carrera, ec.inscripcion " +
            "HAVING COUNT(ec) > 0 " +
            "ORDER BY ec.inscripcion ASC, c.carrera ASC")
    public List<Object> getReportCarrera();
}
