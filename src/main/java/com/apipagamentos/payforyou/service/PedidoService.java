package com.apipagamentos.payforyou.service;

import com.apipagamentos.payforyou.dto.DadosItemRetorno;
import com.apipagamentos.payforyou.dto.DadosPagamento;
import com.apipagamentos.payforyou.dto.DadosPedidoRetorno;
import com.apipagamentos.payforyou.dto.DadosStatusPagamento;
import com.apipagamentos.payforyou.infra.ItemNaoSalvoNoCatalogoException;
import com.apipagamentos.payforyou.infra.PedidoNaoFinalizadoException;
import com.apipagamentos.payforyou.infra.PedidoNaoEncontradoException;
import com.apipagamentos.payforyou.model.ItemPedido;
import com.apipagamentos.payforyou.model.Pagamento;
import com.apipagamentos.payforyou.model.Pedido;
import com.apipagamentos.payforyou.model.StatusPedido;
import com.apipagamentos.payforyou.repository.ItemPedidoRepository;
import com.apipagamentos.payforyou.repository.PagamentoRepository;
import com.apipagamentos.payforyou.repository.PedidoRepository;
import com.apipagamentos.payforyou.service.validacoes.ValidadorPedidoPago;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private ItemPedidoRepository itemRepository;
    @Autowired
    private ValidadorPedidoPago validator;

    @Transactional
    public DadosPedidoRetorno pagarPedido(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new PedidoNaoEncontradoException("Näo foi possivel encontrar esse pedido."));
        if(pedido.getStatus() != StatusPedido.FINALIZADO){
            throw new PedidoNaoFinalizadoException("Pedido não pode ser pago por favor finalize chamando /finalizar.");
        }
        DadosPagamento valorpagamento = new DadosPagamento(pedido);
        Pagamento pagamento = new Pagamento(valorpagamento);
        pagamento.atualizarStatus();
        DadosStatusPagamento statusPagamento = new DadosStatusPagamento(pagamento.getStatus());
        validator.validar(statusPagamento);
        pedido.setStatus(StatusPedido.PAGO);
        pagamentoRepository.save(pagamento);
        DadosPedidoRetorno retorno = new DadosPedidoRetorno(pedido);
        return retorno;
    }
    @Transactional
    public DadosItemRetorno adicionarItemAPedido(Long idpedido,Long iditem){
        Pedido pedido = pedidoRepository.findById(idpedido).orElseThrow(()-> new PedidoNaoEncontradoException("Näo foi possivel encontrar esse pedido."));
        ItemPedido itemNovo = itemRepository.findById(iditem).orElseThrow(()-> new ItemNaoSalvoNoCatalogoException("Este item näo esta presente no catalogo."));
        pedido.adicionarItem(itemNovo);
        pedido.recalcular(itemNovo);
        DadosItemRetorno retorno = new DadosItemRetorno(itemNovo.getId(),itemNovo.getProduto(),pedido.getValorTotal());
        return retorno;

    }

}
