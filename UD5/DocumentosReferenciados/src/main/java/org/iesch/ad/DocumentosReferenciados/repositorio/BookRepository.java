package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<BookRef, String> {

    List<BookRef> findByAutoresId(String id);

    // @QUERYS
    @Query("{'precio': {$lt: ?0}, {'anioPublicacion': {$gte: ?1}}}")
    List<BookRef> buscarPorPrecioInferiorYanioSuperior(Double precio, Integer anio);

    @Query("{ $or: [{'precio': {$lt: ?0}}, {'anioPublicacion': {$lt: ?1}}] }")
    List<BookRef> buscarLibrosEconomicosOAntiguos(Double precio, Integer anio);

    BookRef findByTitulo(String titulo);

    @Aggregation(pipeline = {
            "{$lookup: { from: 'autores', localField: 'autores', foreignField: '_id', as: 'autor' }}",
            "{$match : { 'autor.nombre': ?0 }}"
    })
    List<BookRef> findByNombreAutores(String nombre);



}
