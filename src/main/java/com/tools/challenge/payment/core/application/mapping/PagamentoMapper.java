package com.tools.challenge.payment.core.application.mapping;

import com.tools.challenge.payment.core.application.contracts.DescricaoViewModel;
import com.tools.challenge.payment.core.application.contracts.FormaPagamentoViewModel;
import com.tools.challenge.payment.core.application.contracts.PagamentoInputModel;
import com.tools.challenge.payment.core.application.contracts.PagamentoViewModel;
import com.tools.challenge.payment.core.domain.Pagamento;
import com.tools.challenge.payment.core.domain.enums.TipoPagamento;
import com.tools.challenge.payment.core.domain.valueObject.Descricao;
import com.tools.challenge.payment.core.domain.valueObject.FormaPagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class PagamentoMapper implements IPagamentoMapper {
    @Override
    public Pagamento toDomain(PagamentoInputModel input) {
        return Pagamento.builder()
                .cartao(input.cartao())
                .descricao(Descricao.create(
                        new BigDecimal(input.valor()),
                        LocalDateTime.parse(input.dataHora(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                        input.estabelecimento()
                ))
                .formaPagamento(new FormaPagamento(
                        TipoPagamento.valueOf(input.formaPagamento().tipo().replace(" ", "_")
                                .toUpperCase()),
                        input.formaPagamento().parcelas()
                )).build();
    }

    @Override
    public PagamentoViewModel toViewModel(Pagamento pagamento) {
        return new PagamentoViewModel(
                pagamento.getCartao(),
                pagamento.getId(),
                new DescricaoViewModel(
                        pagamento.getDescricao().getValor(),
                        pagamento.getDescricao().getDataHora(),
                        pagamento.getDescricao().getEstabelecimento(),
                        pagamento.getDescricao().getNsu(),
                        pagamento.getDescricao().getCodigoAutorizacao(),
                        pagamento.getDescricao().getStatus().name()
                ),
                new FormaPagamentoViewModel(
                        pagamento.getFormaPagamento().getTipo().getFormaPagamento(),
                        pagamento.getFormaPagamento().getParcelas()
                )
        );
    }
}
