package com.provanetprecision.lanchoneteservice.service.mapper;

import com.provanetprecision.lanchoneteservice.domain.Pedido;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoMapper extends EntityMapper<PedidoDTO, Pedido> {

}