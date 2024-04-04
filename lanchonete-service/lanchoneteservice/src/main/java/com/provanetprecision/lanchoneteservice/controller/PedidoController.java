package com.provanetprecision.lanchoneteservice.controller;

import com.provanetprecision.lanchoneteservice.service.PedidoService;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService service;

    @PostMapping
    public ResponseEntity<PedidoDTO> salvar(@Valid @RequestBody PedidoDTO dto) {
        PedidoDTO pedidoDTO = service.salvar(dto);

        return new ResponseEntity<>(pedidoDTO, HttpStatus.CREATED);
    }

    @PostMapping("/adicionar-produto-pedido")
    public ResponseEntity<PedidoProdutoDTO> adicionarProduto(@Valid @RequestBody PedidoProdutoDTO dto){
        PedidoProdutoDTO pedidoProdutoDTO = service.adicionarProduto(dto);
        return new ResponseEntity<>(pedidoProdutoDTO, HttpStatus.OK);
    }

    @PutMapping("/remover-produto-pedido")
    public ResponseEntity<PedidoProdutoDTO> removerProduto(@Valid @RequestBody PedidoProdutoDTO dto){
        PedidoProdutoDTO pedidoProdutoDTO = service.removerProduto(dto);

        if (Objects.isNull(pedidoProdutoDTO)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidoProdutoDTO, HttpStatus.OK);
    }

}
