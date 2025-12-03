package org.iesch.ad.ResProductos.modelo;

import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class Product {
    private long id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private int stock;
}