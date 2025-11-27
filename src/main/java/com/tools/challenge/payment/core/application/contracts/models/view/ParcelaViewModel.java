package com.tools.challenge.payment.core.application.contracts.models.view;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ParcelaViewModel(
        int numero,
        BigDecimal valor,
        LocalDate dataVencimento,
        String status
) {
}
