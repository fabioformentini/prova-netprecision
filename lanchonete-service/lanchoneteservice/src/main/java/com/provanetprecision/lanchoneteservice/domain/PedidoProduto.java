package com.provanetprecision.lanchoneteservice.domain;

import com.provanetprecision.lanchoneteservice.domain.pk.PedidoProdutoPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "rel_pedido_produto")
@AllArgsConstructor
@NoArgsConstructor
public class PedidoProduto {

    @EmbeddedId
    private PedidoProdutoPK id;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

}