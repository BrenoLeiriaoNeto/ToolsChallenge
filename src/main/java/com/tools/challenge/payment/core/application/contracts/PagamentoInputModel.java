package com.tools.challenge.payment.core.application.contracts;

public record PagamentoInputModel(
        String cartao,
        String valor,
        String dataHora,
        String estabelecimento,
        FormaPagamentoInputModel formaPagamento
) {
}
