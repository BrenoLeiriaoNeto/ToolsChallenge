package com.tools.challenge.payment.core.application.contracts.models.view;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ParcelaViewModel(
        int numero,
        BigDecimal valor,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime dataVencimento,
        String status
) {
}
