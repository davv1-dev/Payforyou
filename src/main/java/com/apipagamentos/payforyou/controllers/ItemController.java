package com.apipagamentos.payforyou.controllers;

import com.apipagamentos.payforyou.dto.DadosItem;
import com.apipagamentos.payforyou.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService service;


    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarItem(@RequestBody @Valid DadosItem dadosItem, UriComponentsBuilder uribuilder){
        var itemSalvo = service.salvarItemNoCatalogo(dadosItem);
        var uri = uribuilder.path("/item/{id}").buildAndExpand(itemSalvo.getId()).toUri();
        return ResponseEntity.created(uri).body(itemSalvo);
    }


    @GetMapping("/{id}")
    public ResponseEntity buscarPedidoPorId(@PathVariable Long id){
        var item = service.buscarPorId(id);
        return ResponseEntity.ok(item);
    }
}
