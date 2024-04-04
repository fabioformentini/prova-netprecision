package com.provanetprecision.lanchoneteservice.repository;

import com.provanetprecision.lanchoneteservice.domain.PedidoProduto;
import com.provanetprecision.lanchoneteservice.domain.pk.PedidoProdutoPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProduto, PedidoProdutoPK> {

    @Query("SELECT pp.quantidade " +
            "FROM PedidoProduto pp " +
            "where pp.id.idPedido = :idPedido and pp.id.idProduto = :idProduto")
    Integer findQuantidadeProdutoByIdPedidoAndIdProduto(Long idPedido, Long idProduto);

}