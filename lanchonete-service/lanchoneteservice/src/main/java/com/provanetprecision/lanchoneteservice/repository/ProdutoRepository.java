package com.provanetprecision.lanchoneteservice.repository;

import com.provanetprecision.lanchoneteservice.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p.preco" +
            " FROM Produto p " +
            " WHERE p.id = :id")
    BigDecimal findPrecoByProdutoId(Long id);

}