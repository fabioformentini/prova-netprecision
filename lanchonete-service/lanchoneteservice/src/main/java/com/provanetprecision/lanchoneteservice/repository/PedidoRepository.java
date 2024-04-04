package com.provanetprecision.lanchoneteservice.repository;

import com.provanetprecision.lanchoneteservice.domain.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}