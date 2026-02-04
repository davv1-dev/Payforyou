package com.apipagamentos.payforyou.controllers;

import com.apipagamentos.payforyou.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;

    @PostMapping("/{id}/adicionaritem/{id2}")
    public ResponseEntity adicionarItemPorId(@PathVariable Long id,@PathVariable Long id2){
    var retorno = service.adicionarItemAPedido(id,id2);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(retorno);
    }

    @PostMapping("/pagar/{id}")
    public ResponseEntity pagarPedido(@PathVariable Long id){
        var dadosatualizados = service.pagarPedido(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dadosatualizados);
    }
}
