package com.apipagamentos.payforyou.model;

import com.apipagamentos.payforyou.dto.DadosPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Table(name = "pagamento")
@Entity(name = "pagamento")
@Getter
@Setter
@AllArgsConstructor
public class Pagamento {
    private StatusPagamento status;
    private LocalDateTime data;
    @OneToOne(fetch = FetchType.LAZY)
    private BigDecimal valorpagamento;
    private Pedido pedido;
    public Pagamento(){
        this.status = StatusPagamento.PENDENTE;
    }

}
