package com.provanetprecision.lanchoneteservice.domain.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProdutoPK implements Serializable {

    @Column(name = "id_pedido")
    private Long idPedido;

    @Column(name = "id_produto")
    private Long idProduto;
}