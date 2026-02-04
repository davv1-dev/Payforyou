package com.apipagamentos.payforyou.dto;

import com.apipagamentos.payforyou.model.ItemPedido;
import com.apipagamentos.payforyou.model.Pedido;

import java.math.BigDecimal;
import java.util.List;

public record DadosListaItemPedido(Long id, List<ItemPedido> itensDoPedido, BigDecimal valorTotal) {
    public DadosListaItemPedido(Pedido pedido){
        this(pedido.getId(),pedido.getItens(),pedido.getValorTotal());
    }
}
