package org.iesch.ad.excepcionesSpring.exception;

import org.iesch.ad.excepcionesSpring.modelo.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Maneja la excepcion cuando no se encuentra el estudiante
     * Devuelve un 404 NOT FOUND
     */
    @ExceptionHandler(EstudianteNoEncontradoException.class)
    public ResponseEntity<ErrorResponseDto> manejarEstudianteNoEncontrado (
            EstudianteNoEncontradoException ex, WebRequest request
    ) {
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "not found",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    };
}
