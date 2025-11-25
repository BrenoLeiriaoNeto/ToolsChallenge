package com.tools.challenge.payment.core.application.contracts;

public record PagamentoViewModel(
        String cartao,
        String id,
        DescricaoViewModel descricao,
        FormaPagamentoViewModel formaPagamento
) {
}
