package org.iesch.ad.excepcionesSpring.exception;

public class EmailDuplicadoException extends RuntimeException {
    public EmailDuplicadoException(String email) {
        super("El email " + email + " ya esta registrado");
    }
}
