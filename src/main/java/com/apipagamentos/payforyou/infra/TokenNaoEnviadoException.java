package com.apipagamentos.payforyou.infra;

public class TokenNaoEnviadoException extends RuntimeException {
    public TokenNaoEnviadoException(String message) {
        super(message);
    }
}
