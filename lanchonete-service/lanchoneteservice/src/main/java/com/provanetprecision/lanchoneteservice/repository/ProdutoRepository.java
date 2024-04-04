package com.provanetprecision.lanchoneteservice.repository;

import com.provanetprecision.lanchoneteservice.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}