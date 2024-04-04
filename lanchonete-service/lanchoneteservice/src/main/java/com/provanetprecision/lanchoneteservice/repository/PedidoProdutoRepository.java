package com.provanetprecision.lanchoneteservice.repository;

import com.provanetprecision.lanchoneteservice.domain.PedidoProduto;
import com.provanetprecision.lanchoneteservice.domain.pk.PedidoProdutoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, PedidoProdutoPK> {

    @Query("SELECT pp.quantidade " +
            "FROM PedidoProduto pp " +
            "where pp.id.idPedido = :idPedido and pp.id.idProduto = :idProduto")
    Integer findQuantidadeProdutoByIdPedidoAndIdProduto(Long idPedido, Long idProduto);

    @Query("SELECT sum (prod.preco * pp.quantidade) " +
            "FROM Pedido ped " +
            "JOIN PedidoProduto pp ON ped.id = pp.id.idPedido " +
            "JOIN Produto prod ON prod.id = pp.id.idProduto " +
            "WHERE ped.id = :idPedido " +
            "GROUP BY ped.id" )
    BigDecimal calcularPrecoTotalPedidoById(Long idPedido);


}