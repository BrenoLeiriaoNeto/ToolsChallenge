package com.tools.challenge.payment.presentation.controller;

import com.tools.challenge.payment.core.application.contracts.models.input.PagamentoInputModel;
import com.tools.challenge.payment.core.application.contracts.models.view.PagamentoViewModel;
import com.tools.challenge.payment.core.application.contracts.interfaces.IPagamentoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<PagamentoViewModel> criarPagamento(@Valid @RequestBody PagamentoInputModel input) {
        PagamentoViewModel viewModel = pagamentoService.criarPagamento(input);
        return ResponseEntity.status(HttpStatus.CREATED).body(viewModel);
    }

    @GetMapping
    public ResponseEntity<List<PagamentoViewModel>> consultarTodos() {
        List<PagamentoViewModel> pagamentos = pagamentoService.consultaTodos();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoViewModel> consulta(@PathVariable String id) {
        PagamentoViewModel viewModel = pagamentoService.consulta(id);
        return ResponseEntity.ok(viewModel);
    }

    @GetMapping("/estorno/{id}")
    public ResponseEntity<PagamentoViewModel> consultaEstorno(@PathVariable String id) {
        PagamentoViewModel viewModel = pagamentoService.consultaEstorno(id);
        return ResponseEntity.ok(viewModel);
    }

    @PutMapping("/estorno/{id}")
    public ResponseEntity<PagamentoViewModel> estornarPagamento(@PathVariable String id) {
        PagamentoViewModel viewModel = pagamentoService.estornarPagamento(id);
        return ResponseEntity.ok(viewModel);
    }
}
