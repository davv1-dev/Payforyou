package com.apipagamentos.payforyou.dto;

import com.apipagamentos.payforyou.model.ItemPedido;
import com.apipagamentos.payforyou.model.Pedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record DadosPedidosListagem(Long id,LocalDateTime data, List<ItemPedido> itens, BigDecimal valorTotalPedido) {
    public DadosPedidosListagem(Pedido pedido){
        this(pedido.getId(),pedido.getDataCriacao(),pedido.getItens(),pedido.getValorTotal());
    }
}
