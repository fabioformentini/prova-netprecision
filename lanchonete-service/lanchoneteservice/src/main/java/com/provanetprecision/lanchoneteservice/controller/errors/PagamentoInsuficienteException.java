package com.provanetprecision.lanchoneteservice.controller.errors;

public class PagamentoInsuficienteException extends RuntimeException{
    public PagamentoInsuficienteException(String message) {
        super(message);
    }
}
