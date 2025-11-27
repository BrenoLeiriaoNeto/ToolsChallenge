package com.tools.challenge.payment.core.application.contracts.models.input;

import jakarta.validation.Valid;

public record PagamentoInputModel(
        @Valid
        TransacaoInputModel transacao
) {
}
