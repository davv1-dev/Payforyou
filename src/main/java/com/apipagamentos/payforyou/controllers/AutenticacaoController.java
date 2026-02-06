package com.apipagamentos.payforyou.controllers;

import com.apipagamentos.payforyou.dto.DadosAutenticacao;
import com.apipagamentos.payforyou.dto.DadosTokenJWT;
import com.apipagamentos.payforyou.infra.security.TokenService;
import com.apipagamentos.payforyou.model.Usuario;
import com.apipagamentos.payforyou.service.AutenticacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private AutenticacaoService service;
    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(),dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        DadosTokenJWT tokenRetorno = new DadosTokenJWT(tokenJWT);
        return ResponseEntity.ok(tokenRetorno);
    }
    @PostMapping("/cadastrar")
    public ResponseEntity cadastrarUsuario(@RequestBody @Valid DadosAutenticacao dados, UriComponentsBuilder uriBuilder){
    var usuarioASerSalvo = service.cadastrarUsuario(dados);
    var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuarioASerSalvo.getId()).toUri();
    return ResponseEntity.created(uri).body(dados);
    }
}
