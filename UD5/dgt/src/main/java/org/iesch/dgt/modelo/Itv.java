package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Itv {
    private Boolean enVigor;
    private LocalDateTime fechaUltimaInspeccion;
    private LocalDateTime fechaCaducidad;
    private String numeroInforme;
    private String estacionITV;
}
