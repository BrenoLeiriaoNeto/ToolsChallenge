package com.tools.challenge.payment.core.domain.valueObject;

import com.tools.challenge.payment.core.domain.enums.StatusPagamento;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
}
