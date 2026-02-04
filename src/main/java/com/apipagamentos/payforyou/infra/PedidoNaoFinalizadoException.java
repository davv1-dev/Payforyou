package com.apipagamentos.payforyou.infra;

public class PedidoNaoFinalizadoException extends RuntimeException {
    public PedidoNaoFinalizadoException(String message) {
        super(message);
    }
}
