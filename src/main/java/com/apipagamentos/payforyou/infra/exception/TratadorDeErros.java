package com.apipagamentos.payforyou.infra.exception;

import com.apipagamentos.payforyou.infra.ValidatorException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity tratandoRegraDeNegocio(ValidatorException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
