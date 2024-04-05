package com.provanetprecision.lanchoneteservice.repository;

import com.provanetprecision.lanchoneteservice.domain.Pedido;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT NEW com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoListDTO(" +
            "pp.id.idPedido," +
            "p.id," +
            "p.nome," +
            "pp.quantidade) " +
            "FROM " +
            "   Produto p " +
            "   join PedidoProduto pp on p.id = pp.id.idProduto and pp.id.idPedido = :idPedido ")
    List<PedidoProdutoListDTO> buscarProdutosPedidosById(Long idPedido);

}