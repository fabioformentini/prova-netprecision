package com.provanetprecision.lanchoneteservice.controller.errors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(PedidoNotFoundException.class)
    public ResponseEntity<String> handlePedidoNotFoundException(PedidoNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PagamentoInsuficienteException.class)
    public ResponseEntity<String> handlePagamentoInsuficienteException(PagamentoInsuficienteException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(QuantidadeProdutoException.class)
    public ResponseEntity<String> handleQuantidadeProdutoException(QuantidadeProdutoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(PedidoFechadoException.class)
    public ResponseEntity<String> handlePedidoFechadoException(PedidoFechadoException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
