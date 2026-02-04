package com.apipagamentos.payforyou.infra;

public class ItemNaoSalvoNoCatalogoException extends RuntimeException {
    public ItemNaoSalvoNoCatalogoException(String message) {
        super(message);
    }
}
