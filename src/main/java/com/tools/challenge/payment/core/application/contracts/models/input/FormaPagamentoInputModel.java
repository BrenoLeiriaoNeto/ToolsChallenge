package com.tools.challenge.payment.core.application.contracts.models.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record FormaPagamentoInputModel(
        @NotBlank
        String tipo,
        @Pattern(regexp = "\\d+", message = "Parcelas deve ser um numero inteiro")
        String parcelas
) {
}
