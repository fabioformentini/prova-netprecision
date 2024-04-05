package com.provanetprecision.lanchoneteservice.controller.errors;

public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException(String message) {
        super(message);
    }
}
