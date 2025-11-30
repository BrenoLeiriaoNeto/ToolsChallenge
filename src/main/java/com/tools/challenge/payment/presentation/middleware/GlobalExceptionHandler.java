package com.tools.challenge.payment.presentation.middleware;

import com.tools.challenge.payment.core.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PagamentoNaoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handlePagamentoNaoEncontradoException(
            PagamentoNaoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(PagamentoJaEstornadoException.class)
    public ResponseEntity<Map<String, String>> handlePagamentoJaEstornadoException(
            PagamentoJaEstornadoException ex ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(PagamentoAvistaException.class)
    public ResponseEntity<Map<String, String>> handlePagamentoAvistaException(
            PagamentoAvistaException ex ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(NenhumPagamentoEncontradoException.class)
    public ResponseEntity<Map<String, String>> handleNenhumPagamentoEncontradoException(
            NenhumPagamentoEncontradoException ex ) {
        return ResponseEntity.status(200)
                .body(Map.of("mensagem", ex.getMessage()));
    }

    @ExceptionHandler(PagamentoNegadoException.class)
    public ResponseEntity<Map<String, String>> handlePagamentoNegadoException(
            PagamentoNegadoException ex ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(TransacaoJaExistenteException.class)
    public ResponseEntity<Map<String, String>> handleTransacaoJaExistenteException(
            TransacaoJaExistenteException ex ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidacao(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> {
                    String field = fieldError.getField();
                    String simpleField = field.contains(".")
                            ? field.substring(field.lastIndexOf(".") + 1)
                            : field;
                    return simpleField + ": " + fieldError.getDefaultMessage();
                })
                .toList();

        return ResponseEntity.status(422)
                .body(Map.of("erros", erros));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("erro", "Erro interno inesperado."));
    }
}
