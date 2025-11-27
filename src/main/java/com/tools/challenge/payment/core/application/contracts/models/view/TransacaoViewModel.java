package com.tools.challenge.payment.core.application.contracts.models.view;

import java.util.List;
import java.util.UUID;

public record TransacaoViewModel(
        String cartao,
        UUID id,
        DescricaoViewModel descricao,
        FormaPagamentoViewModel formaPagamento,
        List<ParcelaViewModel> parcelas
) {
}
