package com.tools.challenge.payment.core.application.mapping;

import com.tools.challenge.payment.core.application.contracts.models.input.PagamentoInputModel;
import com.tools.challenge.payment.core.application.contracts.models.input.TransacaoInputModel;
import com.tools.challenge.payment.core.application.contracts.models.view.*;
import com.tools.challenge.payment.core.application.utils.CartaoMaskUtil;
import com.tools.challenge.payment.core.domain.Pagamento;
import com.tools.challenge.payment.core.domain.enums.TipoPagamento;
import com.tools.challenge.payment.core.domain.valueObject.Descricao;
import com.tools.challenge.payment.core.domain.valueObject.FormaPagamento;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class PagamentoMapper implements IPagamentoMapper {
    @Override
    public Pagamento toDomain(PagamentoInputModel input) {
        System.out.println("AQUI ESTA VINDO AS INFORMACOES DO PAGAMENTO ----> " + input);

        TransacaoInputModel tim = input.transacao();

        return Pagamento.builder()
                .cartao(tim.cartao())
                .descricao(Descricao.create(
                        new BigDecimal(tim.descricao().valor()),
                        LocalDateTime.parse(tim.descricao().dataHora(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                        tim.descricao().estabelecimento()
                ))
                .formaPagamento(new FormaPagamento(
                        TipoPagamento.valueOf(tim.formaPagamento().tipo().replace(" ", "_")
                                .toUpperCase()),
                        Integer.parseInt(tim.formaPagamento().parcelas())
                )).build();
    }

    @Override
    public PagamentoViewModel toViewModel(Pagamento pagamento) {
        List<ParcelaViewModel> parcelasView = pagamento.getParcelas().stream()
                .map(p -> new ParcelaViewModel(
                        p.getNumero(),
                        p.getValor(),
                        p.getDataVencimento(),
                        p.getStatus().name()
                ))
                .toList();

        TransacaoViewModel tvm = new TransacaoViewModel(
                CartaoMaskUtil.mascarar(pagamento.getCartao()),
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
                ),
                parcelasView
        );

        return new PagamentoViewModel(tvm);
    }
}
