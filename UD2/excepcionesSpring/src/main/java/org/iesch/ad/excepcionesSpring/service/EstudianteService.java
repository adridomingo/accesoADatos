package org.iesch.ad.excepcionesSpring.service;

import org.iesch.ad.excepcionesSpring.exception.EstudianteNoEncontradoException;
import org.iesch.ad.excepcionesSpring.modelo.Estudiante;
import org.iesch.ad.excepcionesSpring.modelo.dto.EstudianteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EstudianteService {

    @Autowired
    Map<Long, Estudiante> estudiantes;

    public List<EstudianteResponseDTO> obtenerTodos() {
        List<Estudiante> listaEstudiantes = new ArrayList<>(estudiantes.values());
        List<EstudianteResponseDTO> estudianteResponseDTOS =
                listaEstudiantes.stream().map(EstudianteResponseDTO::new).toList();

        return estudianteResponseDTOS;
    }

    public EstudianteResponseDTO obtenerPorId(Long id) {
        Estudiante estudiante = estudiantes.get(id);
        if (estudiante != null) {
            return new EstudianteResponseDTO(estudiante);
        } else {
            throw new EstudianteNoEncontradoException(id);
        }
    }
}
