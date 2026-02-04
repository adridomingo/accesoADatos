package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.BookRef;
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
}
