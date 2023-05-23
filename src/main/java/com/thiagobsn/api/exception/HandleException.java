package com.thiagobsn.api.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class HandleException {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleErro404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleErro400(MethodArgumentNotValidException ex) {

        var erros = ex.getFieldErrors().stream().map(DadosErro::new);
        return ResponseEntity.badRequest().body(erros);

    }

    public record DadosErro(String campo, String mensagem) {

        public DadosErro(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
