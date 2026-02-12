package org.iesch.hotel.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.hotel.modelo.enums.EstadoHabitacion;
import org.iesch.hotel.modelo.enums.TipoHabitacion;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entidad que representa una habitación del hotel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "habitaciones")
public class Habitacion {

    @Id
    private String id;

    @Indexed(unique = true)
    private String numero;

    private TipoHabitacion tipoHabitacion;
    private Double precioBaseNoche;
    private EstadoHabitacion estado;
    private Integer planta;
    private Integer capacidadMaxima;
    private Integer metrosCuadrados;
    private List<String> caracteristicas;
    private LocalDateTime ultimaLimpieza;
    private LocalDateTime fechaUltimoMantenimiento;
    private String motivoMantenimiento;
}

