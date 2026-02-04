package com.apipagamentos.payforyou.dto;

import com.apipagamentos.payforyou.model.Pagamento;
import com.apipagamentos.payforyou.model.Pedido;
import com.apipagamentos.payforyou.model.StatusPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DadosPagamento(BigDecimal valorpagamento) {
    public DadosPagamento(Pedido pedido){
        this(pedido.getValorTotal());
    }
}
