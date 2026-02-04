package com.apipagamentos.payforyou.service;

import com.apipagamentos.payforyou.dto.DadosDetalhamentoItem;
import com.apipagamentos.payforyou.dto.DadosItem;
import com.apipagamentos.payforyou.infra.ItemNaoSalvoNoCatalogoException;
import com.apipagamentos.payforyou.model.ItemPedido;
import com.apipagamentos.payforyou.repository.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    private ItemPedidoRepository repository;

    public ItemPedido salvarItemNoCatalogo(DadosItem dadosItem){
        ItemPedido itemSalvo = new ItemPedido(dadosItem);
        repository.save(itemSalvo);
        return itemSalvo;
    }

    public DadosDetalhamentoItem buscarPorId(Long id) {
        DadosDetalhamentoItem dados = new DadosDetalhamentoItem(repository.findById(id).orElseThrow(()-> new ItemNaoSalvoNoCatalogoException("Este item näo esta presente no catalogo.")));
        return dados;
    }
}
