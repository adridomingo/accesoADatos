package org.iesch.ad.DocumentosReferenciados.repositorio;

import org.iesch.ad.DocumentosReferenciados.modelo.AutoresRef;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoresRepository extends MongoRepository<AutoresRef, String > {

}
