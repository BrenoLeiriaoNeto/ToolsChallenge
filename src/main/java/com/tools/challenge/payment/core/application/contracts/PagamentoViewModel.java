package com.tools.challenge.payment.core.application.contracts;

import java.util.List;
import java.util.UUID;

public record PagamentoViewModel(
        String cartao,
        UUID id,
        DescricaoViewModel descricao,
        FormaPagamentoViewModel formaPagamento,
        List<ParcelaViewModel> parcelas
) {
}
