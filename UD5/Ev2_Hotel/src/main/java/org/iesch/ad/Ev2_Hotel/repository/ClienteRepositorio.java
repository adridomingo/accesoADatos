package org.iesch.ad.Ev2_Hotel.repository;

import org.iesch.ad.Ev2_Hotel.modelo.Cliente;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClienteRepositorio extends MongoRepository<Cliente, String> {

    Optional <Cliente> findByDni(String dniCliente);
}
