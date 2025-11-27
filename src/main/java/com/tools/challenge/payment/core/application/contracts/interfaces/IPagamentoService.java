package com.tools.challenge.payment.core.application.contracts.interfaces;

import com.tools.challenge.payment.core.application.contracts.models.input.PagamentoInputModel;
import com.tools.challenge.payment.core.application.contracts.models.view.PagamentoViewModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPagamentoService {

    PagamentoViewModel criarPagamento(PagamentoInputModel input);
    Optional<PagamentoViewModel> estorno(UUID id);
    Optional<PagamentoViewModel> consulta(UUID id);
    List<PagamentoViewModel> consultaTodos();
}
