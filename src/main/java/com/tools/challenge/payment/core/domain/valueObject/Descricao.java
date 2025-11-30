package com.tools.challenge.payment.core.domain.valueObject;

import com.tools.challenge.payment.core.domain.enums.StatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Descricao {

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private String estabelecimento;

    @Column(nullable = false)
    private String nsu;

    @Column(nullable = false)
    private String codigoAutorizacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPagamento status;

    private static final AtomicLong sequence = new AtomicLong();

    private static String generateNsu() {
        return String.format("%012d", sequence.incrementAndGet());
    }

    private static String generateCodigoAutorizacao() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 9);
    }

    public static Descricao create(BigDecimal valor, LocalDateTime dataHora, String estabelecimento) {
        return Descricao.builder()
                .valor(valor)
                .dataHora(dataHora)
                .estabelecimento(estabelecimento)
                .nsu(generateNsu())
                .codigoAutorizacao(generateCodigoAutorizacao())
                .status(StatusPagamento.AUTORIZADO)
                .build();
    }
}
