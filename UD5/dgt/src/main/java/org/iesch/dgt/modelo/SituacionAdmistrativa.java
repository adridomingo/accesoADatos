package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iesch.dgt.modelo.enums.EstadoVehiculo;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SituacionAdmistrativa {

    private EstadoVehiculo estadoVehiculo;
    private LocalDateTime fechaEstado;
    private String motivoBaja;
    private LocalDateTime fechaBajaTemporal;
    private LocalDateTime fechaFinBajaTemporal;
    private Boolean precintado;
    private LocalDateTime fechaPrecinto;
    private String motivoPrecinto;
}
