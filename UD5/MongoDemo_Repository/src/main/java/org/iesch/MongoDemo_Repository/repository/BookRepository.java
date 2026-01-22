package org.iesch.MongoDemo_Repository.repository;

import org.iesch.MongoDemo_Repository.modelo.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    // ============================ QUERY METHODS
    List<Book> findByTituloContainingIgnoreCase(String titulo);

    List<Book> findByCategoriasIgnoreCase(String categoria);

    List<Book> findByAutoresNombre(String nombre);

    List<Book> findByAutoresNacionalidad(String nacionalidad);

    List<Book> findByPrecioBetween(Double min, Double max);

    List<Book> findByAnioPublicacionGreaterThan(Integer anio);

    // ============================= QUERYS PERSONALIZADAS
    @Query("{'autores.nombre': {$regex: ?0, $options: 'i'}}")
    List<Book> buscarPorAutorNombre(String nombreAutor);

    @Query("{'precio': {$lt: ?0}, 'anioPublicacion': {$gte: ?1}}")
    List<Book> buscarPorPrecioInferiorYanioMayor(Double precio, Integer anio);
}
