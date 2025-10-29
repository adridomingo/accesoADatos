package org.iesch.ad.excepcionesSpring.exception;

public class EstudianteNoEncontradoException extends RuntimeException {
    public EstudianteNoEncontradoException(Long id) {
        super("No se encontro el estudiante con id " + id);
    }
}
