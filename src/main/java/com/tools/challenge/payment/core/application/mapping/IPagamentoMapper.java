package com.tools.challenge.payment.core.application.mapping;

import com.tools.challenge.payment.core.application.contracts.models.input.PagamentoInputModel;
import com.tools.challenge.payment.core.application.contracts.models.view.PagamentoViewModel;
import com.tools.challenge.payment.core.domain.Pagamento;

public interface IPagamentoMapper {

    Pagamento toDomain(PagamentoInputModel input);
    PagamentoViewModel toViewModel(Pagamento pagamento);
}
