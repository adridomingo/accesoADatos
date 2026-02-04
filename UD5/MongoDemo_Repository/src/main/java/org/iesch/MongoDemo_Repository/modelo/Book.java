package org.iesch.MongoDemo_Repository.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book {
    String id;
    String titulo;
    String isbn;
    Integer anioPublicacion;
    Double precio;
    Integer numeroPaginas;
    String editorial;

    @DocumentReference
    List<Autor> autores;
    List<String> categorias;

    public Book(String titulo, String isbn, Integer anioPublicacion, Double precio, Integer numeroPaginas, String editorial, List<Autor> autores, List<String> categorias) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.anioPublicacion = anioPublicacion;
        this.precio = precio;
        this.numeroPaginas = numeroPaginas;
        this.editorial = editorial;
        this.autores = autores;
        this.categorias = categorias;
    }
}
