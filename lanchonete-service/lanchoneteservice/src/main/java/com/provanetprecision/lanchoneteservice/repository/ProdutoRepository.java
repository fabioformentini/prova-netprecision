package com.provanetprecision.lanchoneteservice.repository;

import com.provanetprecision.lanchoneteservice.domain.Produto;
import com.provanetprecision.lanchoneteservice.service.dto.DropdownDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    @Query("SELECT p.preco" +
            " FROM Produto p " +
            " WHERE p.id = :id")
    BigDecimal findPrecoByProdutoId(Long id);

    @Query("SELECT NEW com.provanetprecision.lanchoneteservice.service.dto.DropdownDTO(" +
            "P.id, " +
            "P.nome) FROM Produto P")
    List<DropdownDTO> buscarDropdown();

}