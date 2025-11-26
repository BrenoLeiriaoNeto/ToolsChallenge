package com.tools.challenge.payment.core.domain;

import com.tools.challenge.payment.core.domain.enums.StatusParcela;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "parcelas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class Parcela {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal valor;

    @Column(nullable = false)
    private LocalDate dataVencimento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusParcela status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pagamento_id", nullable = false)
    private Pagamento pagamento;

    public static List<Parcela> gerarParcelas(Pagamento pagamento) {
        int qtde = pagamento.getFormaPagamento().getParcelas();
        BigDecimal valorTotal = pagamento.getDescricao().getValor();
        BigDecimal valorParcela = valorTotal.divide(
                BigDecimal.valueOf(qtde), RoundingMode.HALF_UP);

        List<Parcela> parcelas = new ArrayList<>();
        for (int i = 1; i <= qtde; i++) {
            parcelas.add(Parcela.builder()
                    .numero(i)
                    .valor(valorParcela)
                    .dataVencimento(pagamento.getDescricao().getDataHora().toLocalDate().plusMonths(i))
                    .status(StatusParcela.PENDENTE)
                    .pagamento(pagamento)
                    .build());
        }
        return parcelas;
    }
}
