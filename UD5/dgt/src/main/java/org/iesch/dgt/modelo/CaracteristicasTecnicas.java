package org.iesch.dgt.modelo;

import lombok.*;
import org.iesch.dgt.modelo.enums.TipoCombustible;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaracteristicasTecnicas {
    private Integer cilindrada;
    private Integer potencia;
    private String numeroBastidores;
    private TipoCombustible tipoCombustible;
    private String emisiones;
    private Integer plazas;
    private Integer pesoMaximo;
    private String tipoMotor;
}
