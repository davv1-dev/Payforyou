package com.apipagamentos.payforyou.controllers;

import com.apipagamentos.payforyou.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {
    @Autowired
    private PedidoService service;


    @PutMapping("/pagar/{id}")
    public ResponseEntity pagarPedido(@PathVariable Long id){
        var dadosatualizados = service.pagarPedido(id);
        return ResponseEntity.accepted().body(dadosatualizados);
    }
}
