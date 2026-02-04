package com.apipagamentos.payforyou.service.validacoes;

import com.apipagamentos.payforyou.dto.DadosStatusPagamento;
import com.apipagamentos.payforyou.infra.ValidatorException;
import com.apipagamentos.payforyou.model.StatusPagamento;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPedidoPago {


    public void validar(DadosStatusPagamento status) {
        if(status.statusPagamento() == StatusPagamento.REPROVADO){
            throw new ValidatorException("Falha ao realizar o pagamento tente novamente");
        }
    }
}
