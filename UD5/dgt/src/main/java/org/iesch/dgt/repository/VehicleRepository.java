package org.iesch.dgt.repository;

import org.iesch.dgt.modelo.Vehiculos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.lang.ScopedValue;

@Repository
public interface VehicleRepository extends MongoRepository {


    boolean existsByMatricula(String matricula);

    boolean existsByBastidor(String bastidor);

    Vehiculos findByMatricula(String matricula);
}
