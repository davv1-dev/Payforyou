package com.apipagamentos.payforyou.model;

import com.apipagamentos.payforyou.dto.DadosPagamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Table(name = "pagamento")
@Entity(name = "pagamento")
@Getter
@Setter
@AllArgsConstructor
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private StatusPagamento status;
    private LocalDateTime data;
    private BigDecimal valorpagamento;
    @OneToOne(fetch = FetchType.LAZY)
    private Pedido pedido;
    @Transient
    private Random rand = new Random();
    public Pagamento(DadosPagamento dadosPagamento){
        this.status = StatusPagamento.PENDENTE;
        this.data = LocalDateTime.now();
        this.valorpagamento = dadosPagamento.valorpagamento();
    }

    public void atualizarStatus(){
        var prob = rand.nextInt(10)+1;
        if(prob<=3){
            this.status = StatusPagamento.REPROVADO;
        }else {
            this.status = StatusPagamento.APROVADO;
        }
    }

}
