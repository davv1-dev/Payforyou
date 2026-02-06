package com.apipagamentos.payforyou.infra;

public class ErroAoGerarTokenException extends RuntimeException {
    public ErroAoGerarTokenException(String message) {
        super(message);
    }
}
