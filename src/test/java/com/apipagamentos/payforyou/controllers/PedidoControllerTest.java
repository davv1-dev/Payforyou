package com.apipagamentos.payforyou.controllers;

import com.apipagamentos.payforyou.infra.PedidoNaoEncontradoException;
import com.apipagamentos.payforyou.service.PedidoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PedidoControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private PedidoService service;

    @Test
    @DisplayName("Deve retornar 404")
    void pagarPedidoCenario1() throws Exception {
        Mockito.when(service.pagarPedido(1l)).thenThrow(new PedidoNaoEncontradoException("Não foi possivel encontrar pedido"));

        var response = mvc.perform(put("/pedidos/pagar/1"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
    }

}
