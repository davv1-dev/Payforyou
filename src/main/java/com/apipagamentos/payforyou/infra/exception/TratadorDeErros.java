package com.apipagamentos.payforyou.infra.exception;

import com.apipagamentos.payforyou.infra.ItemNaoSalvoNoCatalogoException;
import com.apipagamentos.payforyou.infra.PedidoNaoEncontradoException;
import com.apipagamentos.payforyou.infra.PedidoNaoFinalizadoException;
import com.apipagamentos.payforyou.infra.ValidatorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<List<DadosErro>> tratandoRegraDeNegocio(ValidatorException ex){
        var erro = new DadosErro("Regra", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(erro));
    }
    @ExceptionHandler(PedidoNaoEncontradoException.class)
    public ResponseEntity<List<DadosErro>> tratandoPedidoNaoEncontrado(PedidoNaoEncontradoException ex){
        var erro = new DadosErro("Pedido", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(erro));
    }
    @ExceptionHandler(ItemNaoSalvoNoCatalogoException.class)
    public ResponseEntity<List<DadosErro>> tratandoItemNaoSalvo(ItemNaoSalvoNoCatalogoException ex){
        var erro = new DadosErro("Item",ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(List.of(erro));
    }
    @ExceptionHandler(PedidoNaoFinalizadoException.class)
    public ResponseEntity<List<DadosErro>> tratandoPedidoNaoFinalizado(PedidoNaoFinalizadoException ex){
        var erro = new DadosErro("Pedido",ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(List.of(erro));
    }
    private record DadosErro(String campo,String mensagem){
        public DadosErro(FieldError e){
            this(e.getField(),e.getDefaultMessage());
        }
    }
}
