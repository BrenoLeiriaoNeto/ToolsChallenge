package com.tools.challenge.payment.infrastructure.persistence.query;

import com.tools.challenge.payment.core.domain.Pagamento;
import com.tools.challenge.payment.core.domain.enums.StatusPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPagamentoQueryRepository extends JpaRepository<Pagamento, UUID> {

    Optional<Pagamento> findByIdAndStatus(UUID id, StatusPagamento status);
}
