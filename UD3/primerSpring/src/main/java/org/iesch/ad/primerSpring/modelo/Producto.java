package org.iesch.ad.primerSpring.modelo;

import lombok.*;

//@Data
@Getter
@Setter
@ToString
//@AllArgsConstructor
//@NoArgsConstructor
@Builder
public class Producto {
    int id;
    String nombre;
    float precio;
}
