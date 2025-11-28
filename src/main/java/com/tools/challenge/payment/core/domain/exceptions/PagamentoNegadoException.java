package com.tools.challenge.payment.core.domain.exceptions;

public class PagamentoNegadoException extends RuntimeException {
    public PagamentoNegadoException(String message) {
        super(message);
    }

    public PagamentoNegadoException() {
        super("Este pagamento ja esta negado");
    }
}
