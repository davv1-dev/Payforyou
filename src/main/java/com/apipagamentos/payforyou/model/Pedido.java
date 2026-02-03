package com.apipagamentos.payforyou.model;

import com.apipagamentos.payforyou.dto.DadosPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Table(name = "pedidos")
@Entity(name = "Pedidos")
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCriacao;
    @Enumerated(value = EnumType.STRING)
    private StatusPedido status;
    @Column(precision = 10,scale = 2,nullable = false)
    private BigDecimal valorTotal;
    @OneToMany(mappedBy = "pedido",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    public Pedido (){
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusPedido.CRIADO;
        this.valorTotal = BigDecimal.ZERO;
    }
    public void pedidoPagar(){
        if (this.status == StatusPedido.FINALIZADO){
            DadosPedido pedido = new DadosPedido(this);

        }
    }
    public void adicionarItem(ItemPedido itemPedido){
        itemPedido.setPedido(this);
        this.itens.add(itemPedido);

    }
    public void removerItem(ItemPedido itemPedido){
        this.itens.remove(itemPedido);
        itemPedido.setPedido(null);

    }

    public void recalcular(ItemPedido itemPedido){
        this.valorTotal = itens.stream()
                .map(i -> itemPedido.getSubtotal())
                .reduce(BigDecimal.ZERO,BigDecimal::add);

    }

}
