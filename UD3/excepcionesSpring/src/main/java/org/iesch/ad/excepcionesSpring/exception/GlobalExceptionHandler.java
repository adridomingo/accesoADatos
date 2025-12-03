package org.iesch.ad.excepcionesSpring.exception;

import org.iesch.ad.excepcionesSpring.modelo.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    }

    /**
     * Maneja la excepcion cuando hay un email duplicado
     * Devuelve un HTTP 409 CONFICT
     */
    @ExceptionHandler(EmailDuplicadoException.class)
    public ResponseEntity<ErrorResponseDto> manejarEmailDuplicado (
            EmailDuplicadoException ex, WebRequest request
    ) {
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.CONFLICT.value(),
                "conflict",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /**
     * Maneja excepciones de datos invalidos
     * Devuelve un error 400 Bad Request
     */
    @ExceptionHandler(DatosInvalidosExcepcion.class)
    public ResponseEntity<ErrorResponseDto> manejarDatosInvalidos(
        DatosInvalidosExcepcion ex, WebRequest request
    ) {
        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Manejar excepciones generadas por @Valid
     * Usaremos la lista
     * Devolver 400 BAD REQUEST
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> manejarValidacion (
            MethodArgumentNotValidException ex, WebRequest request
    ) {
        List<String> detalles = new ArrayList<>();

        // Extraer los errores de validacion

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            detalles.add(error.getField() + ": " + error.getDefaultMessage());
        }

        ErrorResponseDto error = new ErrorResponseDto(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "BAD REQUEST",
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                detalles
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
