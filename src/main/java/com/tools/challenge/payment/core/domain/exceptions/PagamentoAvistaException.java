package com.tools.challenge.payment.core.domain.exceptions;

public class PagamentoAvistaException extends RuntimeException {
    public PagamentoAvistaException(String message) {
        super(message);
    }

    public PagamentoAvistaException() {
        super("Pagamentos a vista so podem ter 1 parcela");
    }

}
