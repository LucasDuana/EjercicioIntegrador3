package org.example.ejerciciointegrador3.repositories;

import org.example.ejerciciointegrador3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {

    @Query("SELECT e FROM Estudiante e WHERE e.lu=:lu")
    public Optional<Estudiante> getEstudianteByLu(String lu);

    @Query("SELECT e FROM Estudiante e WHERE e.genero = :genero ORDER BY e.nombre ASC")
    List<Estudiante> getEstudiantesByGenero(@Param("genero") String genero);

    @Query("SELECT e FROM Estudiante e ORDER BY e.nombre ASC")
    public List<Estudiante> getEstudiantesOrderByNombreAfter();

    @Query("SELECT e FROM Estudiante e JOIN EstudianteCarrera ec ON e.dni = ec.estudiante.dni WHERE ec.carrera.carreraId = :carreraId AND e.ciudad = :ciudad")
    List<Estudiante> getEstudianteByCarreraAndCiudad(@Param("carreraId") Long carreraId, @Param("ciudad") String ciudad);

    Optional<Estudiante> getEstudianteByDni(Long dni);

}