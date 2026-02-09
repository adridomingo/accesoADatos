package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Impuestos {
    private Boolean itmvPagado;
    private LocalDateTime fechaUltimoPagoITMV;
    private Integer importeITMV;
    private Integer anioITMV;
}
