package com.apipagamentos.payforyou.dto;

import com.apipagamentos.payforyou.model.Pedido;
import com.apipagamentos.payforyou.model.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosPedidoRetorno(LocalDateTime data, StatusPedido statusPedido, BigDecimal valorTotal) {
    public DadosPedidoRetorno(Pedido pedido){
        this(pedido.getDataCriacao(),pedido.getStatus(),pedido.getValorTotal());
    }
}
