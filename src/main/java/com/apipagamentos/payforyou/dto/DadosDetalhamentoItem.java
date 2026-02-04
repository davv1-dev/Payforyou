package com.apipagamentos.payforyou.dto;

import com.apipagamentos.payforyou.model.ItemPedido;

import java.math.BigDecimal;

public record DadosDetalhamentoItem(Long id, String produto, BigDecimal valorunitario) {
    public DadosDetalhamentoItem(ItemPedido item){
        this(item.getId(), item.getProduto(), item.getPrecoUnitario());
    }
}
