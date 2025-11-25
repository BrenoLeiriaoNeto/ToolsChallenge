package com.tools.challenge.payment.core.domain.enums;

public enum TipoPagamento {
    AVISTA("AVISTA"),
    PARCELADO_LOJA("PARCELADO LOJA"),
    PARCELADO_EMISSOR("PARCELADO EMISSOR");

    private final String valor;
    TipoPagamento(String valor) {
        this.valor = valor;
    }

    public String getFormaPagamento() {
        return valor;
    }
}
