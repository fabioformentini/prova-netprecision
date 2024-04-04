package com.provanetprecision.lanchoneteservice.service.mapper;

import com.provanetprecision.lanchoneteservice.domain.Pedido;
import com.provanetprecision.lanchoneteservice.domain.PedidoProduto;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PedidoProdutoMapper extends EntityMapper<PedidoProdutoDTO, PedidoProduto> {

    @Mapping(target = "id.idProduto", source = "idProduto")
    @Mapping(target = "id.idPedido", source = "idPedido")
    PedidoProduto toEntity(PedidoProdutoDTO dto);

    @InheritInverseConfiguration
    PedidoProdutoDTO toDto(PedidoProduto entity);

}