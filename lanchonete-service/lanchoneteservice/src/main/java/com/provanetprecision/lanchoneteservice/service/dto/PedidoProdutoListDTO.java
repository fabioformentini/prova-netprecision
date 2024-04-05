package com.provanetprecision.lanchoneteservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProdutoListDTO implements Serializable {

    private Long idPedido;
    private Long idProduto;
    private String nome;
    private Integer quantidade;

}