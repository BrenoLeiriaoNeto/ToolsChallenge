package com.tools.challenge.payment.core.domain.exceptions;

public class TransacaoJaExistenteException extends RuntimeException {
    public TransacaoJaExistenteException(String message) {
        super(message);
    }

    public TransacaoJaExistenteException() {
        super("Esta transacao ja existe.");
    }

}
