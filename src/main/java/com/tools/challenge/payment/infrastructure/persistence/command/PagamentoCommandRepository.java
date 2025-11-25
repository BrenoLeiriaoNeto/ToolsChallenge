package com.tools.challenge.payment.infrastructure.persistence.command;

import com.tools.challenge.payment.core.domain.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PagamentoCommandRepository extends JpaRepository<Pagamento, UUID> {
}
