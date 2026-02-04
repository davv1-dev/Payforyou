package com.apipagamentos.payforyou.controllers;

import com.apipagamentos.payforyou.infra.ItemNaoSalvoNoCatalogoException;
import com.apipagamentos.payforyou.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
public class ItemControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockitoBean
    private ItemService service;

    @Test
    @DisplayName("Testando ItemNaoSalvoNoCatalogoException")
    void buscandoItemPorIdCenario1() throws Exception {
        Mockito.when(service.buscarPorId(1L)).thenThrow(new ItemNaoSalvoNoCatalogoException("Este item näo esta presente no catalogo."));

        var response = mvc.perform(get("/item/1"))
                .andReturn().getResponse();
        assertEquals(HttpStatus.NOT_FOUND.value(),response.getStatus());
    }
}
