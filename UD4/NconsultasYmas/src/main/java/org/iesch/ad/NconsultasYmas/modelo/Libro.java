package org.iesch.ad.NconsultasYmas.modelo;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "libros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String isbn;

    private double precio;

    @Column(name = "anio_publicacion")
    private Integer anioPublicacion;

    // Por defecto Eager
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id", nullable = false)
    private Autor autor;

    public Libro(String titulo, String isbn, double precio, Integer anioPublicacion, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.precio = precio;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }
}
