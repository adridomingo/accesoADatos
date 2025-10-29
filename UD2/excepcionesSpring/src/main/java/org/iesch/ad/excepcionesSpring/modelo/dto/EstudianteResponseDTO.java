package org.iesch.ad.excepcionesSpring.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.iesch.ad.excepcionesSpring.modelo.Estudiante;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteResponseDTO {
    private Long id;
    private String nombre;
    private String apellidos;

    public EstudianteResponseDTO (Estudiante estudiante) {
        this.id = estudiante.getId();
        this.nombre = estudiante.getNombre();
        this.apellidos = estudiante.getApellidos();
    }
}
