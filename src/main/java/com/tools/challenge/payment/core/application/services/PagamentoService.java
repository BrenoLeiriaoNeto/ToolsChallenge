package com.tools.challenge.payment.core.application.services;

import com.tools.challenge.payment.core.application.contracts.PagamentoInputModel;
import com.tools.challenge.payment.core.application.contracts.PagamentoViewModel;
import com.tools.challenge.payment.core.application.mapping.IPagamentoMapper;
import com.tools.challenge.payment.core.domain.Pagamento;
import com.tools.challenge.payment.infrastructure.persistence.command.IPagamentoCommandRepository;
import com.tools.challenge.payment.infrastructure.persistence.query.IPagamentoQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final IPagamentoCommandRepository pagamentoCommandRepository;
    private final IPagamentoQueryRepository pagamentoQueryRepository;
    private final IPagamentoMapper mapper;

    public PagamentoViewModel criarPagamento(PagamentoInputModel input) {
        Pagamento pagamento = mapper.toDomain(input);
        Pagamento salvo = pagamentoCommandRepository.save(pagamento);
        return mapper.toViewModel(salvo);
    }

    public Optional<PagamentoViewModel> estorno(UUID id) {
        return pagamentoQueryRepository.findById(id)
                .map(mapper::toViewModel);
    }

    public Optional<PagamentoViewModel> consulta(UUID id) {
            return pagamentoQueryRepository.findById(id)
                    .map(mapper::toViewModel);
    }

    public List<PagamentoViewModel> consultaTodos() {
        return pagamentoQueryRepository.findAll()
                .stream().map(mapper::toViewModel).toList();
    }
}
