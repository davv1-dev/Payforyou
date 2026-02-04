package com.apipagamentos.payforyou.service;

import com.apipagamentos.payforyou.dto.DadosPagamento;
import com.apipagamentos.payforyou.dto.DadosPedidoRetorno;
import com.apipagamentos.payforyou.dto.DadosStatusPagamento;
import com.apipagamentos.payforyou.infra.PedidoNaoCriadoException;
import com.apipagamentos.payforyou.infra.PedidoNaoEncontradoException;
import com.apipagamentos.payforyou.model.Pagamento;
import com.apipagamentos.payforyou.model.Pedido;
import com.apipagamentos.payforyou.model.StatusPedido;
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
    private ValidadorPedidoPago validator;

    @Transactional
    public DadosPedidoRetorno pagarPedido(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(()-> new PedidoNaoEncontradoException("Näo foi possivel encontrar esse pedido."));
        if(pedido.getStatus()!= StatusPedido.CRIADO){
            throw new PedidoNaoCriadoException("Pedido não pode ser pago.");
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

}
