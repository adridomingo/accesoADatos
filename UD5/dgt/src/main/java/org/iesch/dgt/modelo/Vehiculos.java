package org.iesch.dgt.modelo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.iesch.dgt.modelo.enums.TipoVehiculo;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "vehiculos")
@Getter
@Setter
@ToString
//@AllArgsConstructor
@NoArgsConstructor
public class Vehiculos {
    @Id
    private String id;

    @Indexed(unique = true)
    private String matricula;

    @Indexed(unique = true)
    private String bastidor;

    private String marca;
    private String modelo;
    private String color;
    private TipoVehiculo tipoVehiculo;
    private Integer anioFabricacion;
    private LocalDate fechaPrimeraMatriculacion;

    // Embebidos
    private CaracteristicasTecnicas caracteristicasTecnicas;
    private Titular titular;
    private SituacionAdmistrativa situacionAdmistrativa;
    private Itv itv;
    private Impuestos impuestos;

    // Listas
    private List<Multa> multas;
    private List<HistorialTitulares> historialTitulares;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

}
