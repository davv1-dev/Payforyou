package com.apipagamentos.payforyou.repository;

import com.apipagamentos.payforyou.model.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {
}
