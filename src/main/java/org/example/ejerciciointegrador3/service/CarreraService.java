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

        for (Object[] fila : datos) {
            String carrera = (String) fila[0];
            Integer inscripcion = (Integer) fila[1];
            Integer graduacion = (Integer) fila[2];

            reporte.computeIfAbsent(carrera, k -> new HashMap<>());

            Map<Integer, int[]> anios= reporte.get(carrera);
            anios.computeIfAbsent(inscripcion, k -> new int[2]);

            anios.get(inscripcion)[0]++;
            if (graduacion != null) {
                if (graduacion.equals(inscripcion)) {
                    anios.get(inscripcion)[1]++;
                } else {
                    anios.computeIfAbsent(graduacion, k -> new int[2])[1]++;
                }
            }
        }

        return getReporteCarreraDTOS(reporte);
    }

    private  List<ReporteCarreraDTO> getReporteCarreraDTOS(Map<String, Map<Integer, int[]>> reporte) {
        List<ReporteCarreraDTO> resultado = new ArrayList<>();
        for (Map.Entry<String, Map<Integer, int[]>> entry : reporte.entrySet()) {
            String carrera = entry.getKey();
            for (Map.Entry<Integer, int[]> aniosEntry : entry.getValue().entrySet()) {
                Integer anios = aniosEntry.getKey();
                int graduados = aniosEntry.getValue()[1];
                int inscriptos = aniosEntry.getValue()[0];

                if (anios!= 0) {
                    ReporteCarreraDTO dto = new ReporteCarreraDTO(carrera, anios, graduados, inscriptos);
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
