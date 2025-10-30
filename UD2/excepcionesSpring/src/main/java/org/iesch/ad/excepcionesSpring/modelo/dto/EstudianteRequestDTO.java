package org.iesch.ad.excepcionesSpring.modelo.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EstudianteRequestDTO {

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 chars")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 2, max = 50, message = "Los apellidos deben tener entre 2 y 50 chars")
    private String apellidos;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe tener formato valido")
    private String email;

    @NotNull(message = "La edad es obligatorio")
    @Min(value = 16, message = "La edad min es 16 años")
    @Max(value = 99, message = "La edad maxima es 99 años")
    private Integer edad;

    @NotBlank(message = "El ciclo es obligatorio")
    @Pattern(regexp = "DAM|DAW|ASIR|SMR", message = "El ciclo debe ser de informatica")
    private String ciclo;
}
