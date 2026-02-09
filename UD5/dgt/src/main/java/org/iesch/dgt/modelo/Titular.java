package org.iesch.dgt.modelo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Titular {

    @NotBlank(message = "El dni es obligatorio")
    @Pattern(regexp = "^[0-9]{8}[A-Z]$", message = "Ingrese un dni valido")
    private String dni;

    private String nombre;
    private String apellidos;
    private LocalDateTime fechaNacimiento;
    @Email(message = "Ingrese un email valido")
    private String email;

    @Pattern(regexp = "^[0-9]{9}$", message = "Teléfono debe tener 9 dígitos")
    private Integer telefono;

    private DomicilioFiscal domicilioFiscal;

}
