package com.tools.challenge.payment.core.application.services;

import com.tools.challenge.payment.core.application.contracts.models.input.PagamentoInputModel;
import com.tools.challenge.payment.core.application.contracts.models.view.PagamentoViewModel;
import com.tools.challenge.payment.core.application.contracts.interfaces.IPagamentoService;
import com.tools.challenge.payment.core.application.mapping.IPagamentoMapper;
import com.tools.challenge.payment.core.domain.Pagamento;
import com.tools.challenge.payment.core.domain.Parcela;
import com.tools.challenge.payment.core.domain.enums.StatusPagamento;
import com.tools.challenge.payment.core.domain.enums.StatusParcela;
import com.tools.challenge.payment.core.domain.enums.TipoPagamento;
import com.tools.challenge.payment.core.domain.exceptions.*;
import com.tools.challenge.payment.infrastructure.persistence.command.IPagamentoCommandRepository;
import com.tools.challenge.payment.infrastructure.persistence.query.IPagamentoQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService implements IPagamentoService {

    private final IPagamentoCommandRepository pagamentoCommandRepository;
    private final IPagamentoQueryRepository pagamentoQueryRepository;
    private final IPagamentoMapper mapper;

    public PagamentoViewModel criarPagamento(PagamentoInputModel input) {
        if (input.transacao().formaPagamento().tipo().equals("AVISTA") &&
        !input.transacao().formaPagamento().parcelas().equals("1")) {
            throw new PagamentoAvistaException();
        }

        Pagamento pagamento = mapper.toDomain(input);

        BigDecimal saldo = new BigDecimal("200.00");

        List<Parcela> parcelas = Parcela.gerarParcelas(pagamento);

        if (pagamento.getDescricao().getValor().compareTo(saldo) > 0) {
            pagamento.getDescricao().setStatus(StatusPagamento.NEGADO);
            pagamento.setParcelas(parcelas);
            Pagamento negado = pagamentoCommandRepository.save(pagamento);
            return mapper.toViewModel(negado);
        }

        if (pagamento.getFormaPagamento().getTipo().equals(TipoPagamento.AVISTA) && parcelas.size() == 1) {
            parcelas.forEach(p -> p.setStatus(StatusParcela.PAGA));
            pagamento.getDescricao().setStatus(StatusPagamento.AUTORIZADO);
        }

        pagamento.setParcelas(parcelas);

        Pagamento salvo = pagamentoCommandRepository.save(pagamento);

        return mapper.toViewModel(salvo);
    }

    public PagamentoViewModel estornarPagamento(UUID id) {
        Pagamento pagamento = pagamentoQueryRepository.findById(id)
                .orElseThrow(PagamentoNaoEncontradoException::new);

        if (pagamento.getDescricao().getStatus().name().equals("CANCELADO")) {
            throw new PagamentoJaEstornadoException();
        }

        if (pagamento.getDescricao().getStatus().name().equals("NEGADO")) {
            throw new PagamentoNegadoException("Este pagamento foi negado, escolha outro pagamento para estornar");
        }

        pagamento.getDescricao().setStatus(StatusPagamento.CANCELADO);
        Pagamento estornado = pagamentoCommandRepository.save(pagamento);

        return mapper.toViewModel(estornado);
    }

    public PagamentoViewModel consultaEstorno(UUID id) {
        return pagamentoQueryRepository.findByIdAndStatus(id, StatusPagamento.CANCELADO)
                .map(mapper::toViewModel)
                .orElseThrow(PagamentoNaoEncontradoException::new);
    }

    public PagamentoViewModel consulta(UUID id) {
            return pagamentoQueryRepository.findById(id)
                    .map(mapper::toViewModel)
                    .orElseThrow(PagamentoNaoEncontradoException::new);
    }

    public List<PagamentoViewModel> consultaTodos() {
        List<PagamentoViewModel> pagamentos = pagamentoQueryRepository.findAll().stream()
                .map(mapper::toViewModel)
                .toList();

        if (pagamentos.isEmpty()) {
            throw new NenhumPagamentoEncontradoException("Nenhum pagamento a ser listado.");
        }

        return pagamentos;
    }
}
