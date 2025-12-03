package org.iesch.ad.Ejercicio1.modelo;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {
    Long id;
    String name;
    String description;
    Double price;
    String category;
    int stock;
}
