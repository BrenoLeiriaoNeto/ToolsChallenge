package com.tools.challenge.payment.core.domain.exceptions;

import java.util.UUID;

public class PagamentoNaoEncontradoException extends RuntimeException {
    public PagamentoNaoEncontradoException(UUID id) {
        super("Pagamento com ID " + id + " não encontrado.");
    }

    public PagamentoNaoEncontradoException(String message) {
        super(message);
    }

    public PagamentoNaoEncontradoException() {
        super("Pagamento nao encontrado.");
    }
}
