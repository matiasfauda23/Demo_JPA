package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // Indica que esta clase captura errores de todos los @RestControllers
public class GlobalExceptionHandler {

    // Aca le decimos que si ocurre un "ResourceNotFoundException", ejecute este metodo
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> manejarRecursoNoEncontrado(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // Tambien podemos atrapar errores generales (como un error de base de datos)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErroresGenerales(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado: " + ex.getMessage());
    }
}