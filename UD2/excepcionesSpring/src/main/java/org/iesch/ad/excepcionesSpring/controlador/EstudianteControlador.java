package org.iesch.ad.excepcionesSpring.controlador;

import org.iesch.ad.excepcionesSpring.modelo.Estudiante;
import org.iesch.ad.excepcionesSpring.modelo.dto.EstudianteResponseDTO;
import org.iesch.ad.excepcionesSpring.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{id}")
    public ResponseEntity<EstudianteResponseDTO> obtenerUno(@PathVariable Long id) {
        EstudianteResponseDTO estudianteResponseDTO = estudianteService.obtenerPorId(id);
        return ResponseEntity.ok(estudianteResponseDTO);
    }

//    @PostMapping
//    public ResponseEntity<EstudianteResponseDTO> crearNuevo() {
//
//    }

}
