package com.tools.challenge.payment.presentation.controller;

import com.tools.challenge.payment.core.application.contracts.models.input.PagamentoInputModel;
import com.tools.challenge.payment.core.application.contracts.models.view.PagamentoViewModel;
import com.tools.challenge.payment.core.application.contracts.interfaces.IPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/pagamentos")
@RequiredArgsConstructor
public class PagamentoController {

    private final IPagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoViewModel> criarPagamento(@RequestBody PagamentoInputModel input) {
        PagamentoViewModel viewModel = pagamentoService.criarPagamento(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(viewModel);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoViewModel>> consultarTodos() {
        List<PagamentoViewModel> pagamentos = pagamentoService.consultaTodos();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoViewModel> consulta(@PathVariable UUID id) {
        return pagamentoService.consulta(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/estorno/{id}")
    public ResponseEntity<PagamentoViewModel> estorno(@PathVariable UUID id) {
        return pagamentoService.estorno(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
