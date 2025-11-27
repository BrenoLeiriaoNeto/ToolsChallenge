package com.tools.challenge.payment.core.domain.exceptions;

import java.util.UUID;

public class PagamentoJaEstornadoException extends RuntimeException {
    public PagamentoJaEstornadoException(UUID id) {
        super("Pagamento com o ID " + id + " ja foi estornado.");
    }

    public PagamentoJaEstornadoException(String message) {
        super(message);
    }

    public PagamentoJaEstornadoException() {
        super("Pagamento ja foi estornado.");
    }
}
