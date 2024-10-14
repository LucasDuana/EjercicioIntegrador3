package org.example.ejerciciointegrador3.service;

import org.example.ejerciciointegrador3.dtos.ReporteCarreraDTO;
import org.example.ejerciciointegrador3.model.Carrera;
import org.example.ejerciciointegrador3.model.Estudiante;
import org.example.ejerciciointegrador3.model.EstudianteCarrera;
import org.example.ejerciciointegrador3.repositories.CarreraRepository;
import org.example.ejerciciointegrador3.repositories.EstudianteCarreraRepository;
import org.example.ejerciciointegrador3.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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

    public List<ReporteCarreraDTO> generarReporteCarreras() {
        List<Object[]> datos = carreraRepository.obtenerDatosCarreras();
        Map<String, Map<Integer, int[]>> reporte = new HashMap<>();

        // Procesar los resultados
        for (Object[] fila : datos) {
            String carrera = (String) fila[0];
            Integer inscripcion = (Integer) fila[1];
            Integer graduacion = (Integer) fila[2];

            reporte.computeIfAbsent(carrera, k -> new HashMap<>());

            Map<Integer, int[]> años = reporte.get(carrera);
            años.computeIfAbsent(inscripcion, k -> new int[2]);

            años.get(inscripcion)[0]++;
            if (graduacion != null) {
                if (graduacion.equals(inscripcion)) {
                    años.get(inscripcion)[1]++;
                } else {
                    años.computeIfAbsent(graduacion, k -> new int[2])[1]++;
                }
            }
        }

        List<ReporteCarreraDTO> resultado = new ArrayList<>();
        for (Map.Entry<String, Map<Integer, int[]>> entry : reporte.entrySet()) {
            String carrera = entry.getKey();
            for (Map.Entry<Integer, int[]> añoEntry : entry.getValue().entrySet()) {
                Integer año = añoEntry.getKey();
                int graduados = añoEntry.getValue()[1];
                int inscriptos = añoEntry.getValue()[0];

                if (año != 0) {
                    ReporteCarreraDTO dto = new ReporteCarreraDTO(carrera, año, graduados, inscriptos);
                    resultado.add(dto);
                }
            }
        }

        return resultado;
    }

    public Carrera findById(Long id){
        return carreraRepository.findById(id).orElseThrow(null);
    }
}
