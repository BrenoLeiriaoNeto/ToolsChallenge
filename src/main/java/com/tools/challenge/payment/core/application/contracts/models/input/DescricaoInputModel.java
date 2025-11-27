package com.tools.challenge.payment.core.application.contracts.models.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

public record DescricaoInputModel(
        @NotNull
        @Positive
        String valor,
        @NotBlank
        @Pattern(regexp = "\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}",
                message = "Data deve estar no formato dd/MM/yyyy HH:mm:ss")
        String dataHora,
        @NotBlank
        String estabelecimento
) {
}
