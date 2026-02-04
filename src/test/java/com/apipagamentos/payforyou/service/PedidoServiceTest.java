package com.apipagamentos.payforyou.service;

import com.apipagamentos.payforyou.infra.PedidoNaoFinalizadoException;
import com.apipagamentos.payforyou.model.Pedido;
import com.apipagamentos.payforyou.model.StatusPedido;
import com.apipagamentos.payforyou.repository.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class PedidoServiceTest {
    @InjectMocks
    private PedidoService service;

    @Mock
    private PedidoRepository repository;

    @Test
    @DisplayName("Deve falhar se pedido nao estiver FINALIZADO")
    void PagarPedidoCenario2(){

        var pedido = Mockito.mock(Pedido.class);
        Mockito.when(pedido.getStatus())
                .thenReturn(StatusPedido.CRIADO);

        Mockito.when(repository.findById(1L))
                .thenReturn(Optional.of(pedido));

        assertThrows(PedidoNaoFinalizadoException.class,()-> service.pagarPedido(1L));
    }
}
