package org.iesch.ad.Ev2_Hotel.repository;

import org.iesch.ad.Ev2_Hotel.modelo.enums.EstadoHabitacion;
import org.iesch.ad.Ev2_Hotel.modelo.enums.TipoHabitacion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.iesch.hotel.modelo.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepositorio extends MongoRepository<Habitacion, String> {

    List<Habitacion> findByTipoHabitacionAndEstado(TipoHabitacion tipoAsignado, EstadoHabitacion estadoHabitacion);

    Optional<Habitacion> findByNumero(String numeroHabitacion);
}
