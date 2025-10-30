package org.iesch.ad.excepcionesSpring.controlador;

import jakarta.validation.Valid;
import org.iesch.ad.excepcionesSpring.modelo.Estudiante;
import org.iesch.ad.excepcionesSpring.modelo.dto.EstudianteRequestDTO;
import org.iesch.ad.excepcionesSpring.modelo.dto.EstudianteResponseDTO;
import org.iesch.ad.excepcionesSpring.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteControlador {

    @Autowired
    EstudianteService estudianteService;

    @GetMapping
    public ResponseEntity<List<EstudianteResponseDTO>> obtenerTodos() {
        List<EstudianteResponseDTO> studiantes = estudianteService.obtenerTodos();

        return ResponseEntity.ok(studiantes);
    }

    /**
     * GET/api/estudiantes/{id}
     * Obtener estudiantes por id
     * Puede lanzar exceptions EstudianteNoEncontradoException
     */

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> obtenerUno(@PathVariable Long id) {
        EstudianteResponseDTO estudianteResponseDTO = estudianteService.obtenerPorId(id);
        return ResponseEntity.ok(estudianteResponseDTO);
    }

    /**
     * POST/api/estudiantes
     * Creo estudiante nuevo
     * Puede lanzar exceptions EmailDuplicadoException
     */

    @PostMapping
    public ResponseEntity<EstudianteResponseDTO> crearNuevo(@Valid @RequestBody EstudianteRequestDTO estudianteRequestDTO) {
        EstudianteResponseDTO nuevoEstudiante = estudianteService.crear(estudianteRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoEstudiante);
    }

    /**
     * PUT/api/estudiantes/{id}
     * Actualizar estudiante
     * Puede lanzar exceptions EstudianteNoEncontradoException y EmailDuplicadoException
     */

    @PutMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> actualizar(
            @PathVariable Long id, @Valid @RequestBody EstudianteRequestDTO estudianteRequestDTO
    ) {
        EstudianteResponseDTO estudianteActualizado = estudianteService.actualizar(id, estudianteRequestDTO);
        return ResponseEntity.ok(estudianteActualizado);
    }

    /**
     * PUT/api/estudiantes/{id}
     * Borra estudiantes
     * Puede lanzar exceptions EstudianteNoEncontradoException
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar (@PathVariable Long id) {
        estudianteService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * GET /api/estudiantes/ciclo/{ciclo}
     * Obtener estudiantes por ciclo formativo
     * Puede lanzar excepciones DatosInvalidosException o EstudianteNoEncontradoException
     */

    @GetMapping("/ciclo/{ciclo}")
    public ResponseEntity<List<EstudianteResponseDTO>> obtenerPorCiclo(
            @PathVariable String ciclo
    ) {
        List<EstudianteResponseDTO> list = estudianteService.obtenerPorCiclo(ciclo);
        return ResponseEntity.ok(list);
    }
}
