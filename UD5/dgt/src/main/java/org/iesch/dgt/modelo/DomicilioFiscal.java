package org.iesch.dgt.modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DomicilioFiscal {
    private String calle;
    private Integer numero;
    private Integer piso;
    private String puerta;
    private Integer codigoPostal;
    private String localidad;
    private String provincia;
}
