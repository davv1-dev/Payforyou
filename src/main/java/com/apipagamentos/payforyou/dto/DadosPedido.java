package com.apipagamentos.payforyou.dto;

import com.apipagamentos.payforyou.model.Pedido;
import com.apipagamentos.payforyou.model.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosPedido(Long id, LocalDateTime data, StatusPedido statusPedido, BigDecimal valorTotal) {
    public DadosPedido(Pedido pedido){
        this(pedido.getId(), pedido.getDataCriacao(),pedido.getStatus(),pedido.getValorTotal());
    }
}
