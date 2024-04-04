package com.provanetprecision.lanchoneteservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FecharPedidoDTO {

    private Long id;
    private BigDecimal valorPagamento;

}