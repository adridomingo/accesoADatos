package org.iesch.ad.excepcionesSpring.service;

import jakarta.validation.Valid;
import org.iesch.ad.excepcionesSpring.exception.DatosInvalidosExcepcion;
import org.iesch.ad.excepcionesSpring.exception.EmailDuplicadoException;
import org.iesch.ad.excepcionesSpring.exception.EstudianteNoEncontradoException;
import org.iesch.ad.excepcionesSpring.modelo.Estudiante;
import org.iesch.ad.excepcionesSpring.modelo.dto.EstudianteRequestDTO;
import org.iesch.ad.excepcionesSpring.modelo.dto.EstudianteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class EstudianteService {

    @Autowired
    Map<Long, Estudiante> estudiantes;

    @GetMapping
    //@CrossOrigin(origins = "http://localhost")
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

    public EstudianteResponseDTO crear(@Valid EstudianteRequestDTO estudianteRequestDTO) {
        // Validamos que no exista en BBDD(Map) el email
        boolean emailExiste = estudiantes.values().stream()
                .anyMatch(e -> e.getEmail() .equalsIgnoreCase(estudianteRequestDTO.getEmail()));
        if (emailExiste) {
            throw new EmailDuplicadoException(estudianteRequestDTO.getEmail());
        }
        // Crear el usuario
        Long maxKey = estudiantes.keySet().stream().max(Long::compareTo).orElse(1L);
        Estudiante estudiante = new Estudiante(maxKey+1, estudianteRequestDTO.getNombre(), estudianteRequestDTO.getApellidos(),
                estudianteRequestDTO.getEmail(), estudianteRequestDTO.getEdad(), estudianteRequestDTO.getCiclo());
        estudiantes.put(estudiante.getId(), estudiante);
        return new EstudianteResponseDTO(estudiante);
    }

    public EstudianteResponseDTO actualizar(Long id, @Valid EstudianteRequestDTO estudianteRequestDTO) {
        Estudiante estudiante = estudiantes.get(id);
        if (estudiante == null) {
            throw new EstudianteNoEncontradoException(id);
        }
        // Validamos que no exista en BBDD(Map) el email
        boolean emailExiste = estudiantes.values().stream()
                .anyMatch(e -> e.getEmail() .equalsIgnoreCase(estudianteRequestDTO.getEmail()));
        if (emailExiste) {
            throw new EmailDuplicadoException(estudianteRequestDTO.getEmail());
        }
        // Actualizar datos
        estudiante.setNombre(estudianteRequestDTO.getNombre());
        estudiante.setApellidos(estudianteRequestDTO.getApellidos());
        estudiante.setEmail(estudianteRequestDTO.getEmail());
        estudiante.setEdad(estudianteRequestDTO.getEdad());
        estudiante.setCiclo(estudianteRequestDTO.getCiclo());

        return new EstudianteResponseDTO(estudiante);
    }

    public void eliminar(Long id) {
        Estudiante estudiante = estudiantes.get(id);
        if (estudiante == null) {
            throw new EstudianteNoEncontradoException(id);
        }
        // Borrar
        estudiantes.remove(estudiante.getId());

    }

    public List<EstudianteResponseDTO> obtenerPorCiclo(String ciclo) {
        // Validamos ciclo valido
        if (!ciclo.matches("DAM|DAW|ASIR|SMR")) {
            throw new DatosInvalidosExcepcion("Ciclo no valido");
        }
        List<EstudianteResponseDTO> resultado = estudiantes.values().stream().
                filter(e -> e.getCiclo().equalsIgnoreCase(ciclo))
                .map(EstudianteResponseDTO::new)
                .toList();

        if (resultado.isEmpty()) {
            throw new EstudianteNoEncontradoException("No se han encontrado estudiantes de ese ciclo");
        }

        return resultado;
    }
}
