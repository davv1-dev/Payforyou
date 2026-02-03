package com.apipagamentos.payforyou.dto;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public record DadosItem(
        @NotBlank
        String produto,
        @NotBlank
        BigDecimal valorUnitario) {




}
