package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutoresRepository extends MongoRepository<AutoresRef, String > {

    List<AutoresRef> findByNombreContainingIgnoreCase(String nombre);

    List<AutoresRef> findByNacionalidadContainingIgnoreCase(String nacionalidad);

    List<AutoresRef> findByNacionalidadIn(List<String> paises);

}
