package org.iesch.ad.Ev2_Hotel.repository;

import org.iesch.ad.Ev2_Hotel.modelo.Reserva;
import org.iesch.ad.Ev2_Hotel.modelo.enums.EstadoReserva;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RepositorioReservas  extends MongoRepository<Reserva, String> {

    List<Reserva> findByNumeroHabitacionAndEstadoIn(String numero, List<EstadoReserva> estadoActivos);

    Optional<Reserva> findByCodigo(String codigo);
}
