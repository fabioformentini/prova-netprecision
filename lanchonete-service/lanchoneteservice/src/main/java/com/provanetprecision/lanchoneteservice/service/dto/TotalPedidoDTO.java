package com.provanetprecision.lanchoneteservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalPedidoDTO {

    private Long id;
    private List<ProdutoQuantidadeDTO> produtoQuantidadeDTOList;

}