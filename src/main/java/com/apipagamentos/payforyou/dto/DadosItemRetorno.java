package com.apipagamentos.payforyou.dto;

import java.math.BigDecimal;

public record DadosItemRetorno(Long id, String produto, BigDecimal valorTotalPeido) {
}
