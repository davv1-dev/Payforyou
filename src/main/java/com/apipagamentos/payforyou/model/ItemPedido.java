package com.apipagamentos.payforyou.model;

import com.apipagamentos.payforyou.dto.DadosItem;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
@Table(name = "itempedido")
@Entity(name = "ItemPedido")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String produto;
    private Integer quantidade;
    private BigDecimal precoUnitario;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id",nullable = false)
    private Pedido pedido;
    public ItemPedido(DadosItem dadosItem){
        this.produto = dadosItem.produto();
        this.precoUnitario = dadosItem.valorUnitario();
    }
    public BigDecimal getSubtotal(){
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }

}
