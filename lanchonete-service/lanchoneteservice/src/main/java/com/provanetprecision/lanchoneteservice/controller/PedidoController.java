package com.provanetprecision.lanchoneteservice.controller;

import com.provanetprecision.lanchoneteservice.service.PedidoService;
import com.provanetprecision.lanchoneteservice.service.dto.FecharPedidoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoListDTO;
import com.provanetprecision.lanchoneteservice.service.dto.TotalPedidoDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    @PostMapping("/adicionar-produto")
    public ResponseEntity<PedidoProdutoDTO> adicionarProduto(@Valid @RequestBody PedidoProdutoDTO dto){
        PedidoProdutoDTO pedidoProdutoDTO = service.adicionarProduto(dto);
        return new ResponseEntity<>(pedidoProdutoDTO, HttpStatus.OK);
    }

    @PutMapping("/remover-produto")
    public ResponseEntity<PedidoProdutoDTO> removerProduto(@Valid @RequestBody PedidoProdutoDTO dto){
        PedidoProdutoDTO pedidoProdutoDTO = service.removerProduto(dto);

        if (Objects.isNull(pedidoProdutoDTO)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(pedidoProdutoDTO, HttpStatus.OK);
    }

    @GetMapping("/calcular-preco-total/{idPedido}")
    public ResponseEntity<BigDecimal> calcularPrecoTotal(@PathVariable("idPedido") Long id){
        BigDecimal precoTotal = service.calcularPrecoTotalById(id);
        return new ResponseEntity<>(precoTotal, HttpStatus.OK);
    }

    @PostMapping("/fechar-pedido")
    public ResponseEntity<Optional<PedidoDTO>> fecharPedido(@Valid @RequestBody FecharPedidoDTO dto){
        Optional<PedidoDTO> pedidoDTO = service.fecharPedido(dto);
        return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
    }

    @PostMapping("/calcular-preco-total")
    public ResponseEntity<BigDecimal> calcularPrecoTotal(@Valid @RequestBody TotalPedidoDTO dto){
        BigDecimal precoTotal = service.calcularPrecoTotal(dto);
        return new ResponseEntity<>(precoTotal, HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> buscarTodos() {
        List<PedidoDTO> listagem = service.buscarTodos();
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> buscarPorId(@PathVariable("id") Long id) {
        PedidoDTO pedidoDTO = service.buscarPorId(id);
        return new ResponseEntity<>(pedidoDTO, HttpStatus.OK);
    }

    @GetMapping("/buscar-produtos-pedido/{id}")
    public ResponseEntity<List<PedidoProdutoListDTO>> buscarProdutosPedidosById(@PathVariable("id") Long id) {
        List<PedidoProdutoListDTO> listagem = service.buscarProdutosPedidosById(id);
        return new ResponseEntity<>(listagem, HttpStatus.OK);
    }



}
