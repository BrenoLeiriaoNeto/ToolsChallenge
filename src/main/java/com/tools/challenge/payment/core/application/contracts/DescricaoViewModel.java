package com.tools.challenge.payment.core.application.contracts;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record DescricaoViewModel(
        BigDecimal valor,
        LocalDateTime dataHora,
        String estabelecimento,
        String nsu,
        String codigoAutorizacao,
        String status
) {
}
