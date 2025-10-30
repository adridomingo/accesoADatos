package org.iesch.ad.excepcionesSpring.exception;

public class DatosInvalidosExcepcion extends RuntimeException {
    public DatosInvalidosExcepcion(String mensaje) {
        super(mensaje);
    }
}
