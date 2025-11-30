package com.tools.challenge.payment.core.application.contracts.models.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record TransacaoInputModel(
        @NotBlank
        @Pattern(regexp = "\\d{16}", message = "Cartão deve conter 16 dígitos numéricos")
        String cartao,
        @Pattern(regexp = "\\d{19}", message = "O campo 'id' deve conter 19 dígitos numéricos")
        @NotBlank
        String id,
        @Valid
        DescricaoInputModel descricao,
        @Valid
        FormaPagamentoInputModel formaPagamento
) {
}
