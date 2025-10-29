package org.iesch.ad.excepcionesSpring.modelo;


import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {
    private Long id;
    private String nombre;
    private String apellidos;
    private String email;
    private Integer edad;
    private String ciclo; // DAM, DAW ASIR
}
