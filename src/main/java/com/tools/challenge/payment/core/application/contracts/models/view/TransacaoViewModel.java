package com.tools.challenge.payment.core.application.contracts.models.view;

import java.util.List;

public record TransacaoViewModel(
        String cartao,
        String id,
        DescricaoViewModel descricao,
        FormaPagamentoViewModel formaPagamento,
        List<ParcelaViewModel> parcelas
) {
}
