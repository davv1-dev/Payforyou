package com.apipagamentos.payforyou.infra;

public class PedidoNaoCriadoException extends RuntimeException {
    public PedidoNaoCriadoException(String message) {
        super(message);
    }
}
