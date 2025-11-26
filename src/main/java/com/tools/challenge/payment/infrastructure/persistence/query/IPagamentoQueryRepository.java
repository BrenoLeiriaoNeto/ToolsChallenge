package com.tools.challenge.payment.infrastructure.persistence.query;

import com.tools.challenge.payment.core.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IPagamentoQueryRepository extends JpaRepository<Pagamento, UUID> {
}
