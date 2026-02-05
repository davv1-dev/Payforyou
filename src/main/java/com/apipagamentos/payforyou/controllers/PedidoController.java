package com.apipagamentos.payforyou.controllers;

import com.apipagamentos.payforyou.dto.DadosPedidosListagem;
import com.apipagamentos.payforyou.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping
    public ResponseEntity criarPedido(){
        service.criarPedido();
        return ResponseEntity.status(HttpStatus.CREATED).body("Pedido criado com sucesso");
    }
    @PostMapping("/{id}/adicionaritem/{id2}")
    public ResponseEntity adicionarItemPorId(@PathVariable Long id,@PathVariable Long id2){
    var retorno = service.adicionarItemAPedido(id,id2);
    return ResponseEntity.status(HttpStatus.CREATED).body(retorno);
    }

    @PostMapping("/pagar/{id}")
    public ResponseEntity pagarPedido(@PathVariable Long id){
        var dadosatualizados = service.pagarPedido(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosatualizados);
    }

    @GetMapping("/{id}")
    public ResponseEntity obterPedidoPorId(@PathVariable Long id){
    var pedidoEncontrado = service.obterPedidoPorId(id);
    return ResponseEntity.status(HttpStatus.OK).body(pedidoEncontrado);
    }

    @GetMapping
    public ResponseEntity<Page<DadosPedidosListagem>> listarPedidos(@PageableDefault(size = 10,sort = {"data"})Pageable page){
        return ResponseEntity.status(HttpStatus.OK).body(service.listar(page));
    }
}
