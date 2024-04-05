package com.provanetprecision.lanchoneteservice.service;

import com.provanetprecision.lanchoneteservice.controller.errors.PagamentoInsuficienteException;
import com.provanetprecision.lanchoneteservice.controller.errors.PedidoFechadoException;
import com.provanetprecision.lanchoneteservice.controller.errors.PedidoNotFoundException;
import com.provanetprecision.lanchoneteservice.controller.errors.QuantidadeProdutoException;
import com.provanetprecision.lanchoneteservice.domain.Pedido;
import com.provanetprecision.lanchoneteservice.domain.pk.PedidoProdutoPK;
import com.provanetprecision.lanchoneteservice.repository.PedidoProdutoRepository;
import com.provanetprecision.lanchoneteservice.repository.PedidoRepository;
import com.provanetprecision.lanchoneteservice.repository.ProdutoRepository;
import com.provanetprecision.lanchoneteservice.service.dto.FecharPedidoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoListDTO;
import com.provanetprecision.lanchoneteservice.service.dto.ProdutoQuantidadeDTO;
import com.provanetprecision.lanchoneteservice.service.dto.TotalPedidoDTO;
import com.provanetprecision.lanchoneteservice.service.mapper.PedidoMapper;
import com.provanetprecision.lanchoneteservice.service.mapper.PedidoProdutoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PedidoService {

    private final PedidoMapper mapper;
    private final PedidoProdutoMapper pedidoProdutoMapper;
    private final PedidoRepository repository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoDTO salvar(PedidoDTO dto) {
        dto.setPedidoFechado(Boolean.FALSE);
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public PedidoProdutoDTO adicionarProduto(PedidoProdutoDTO dto) {
        PedidoProdutoPK pedidoProdutoPK = new PedidoProdutoPK(dto.getIdPedido(), dto.getIdProduto());
        Optional<PedidoProdutoDTO> pedidoProdutoDTO = findProdutoPedidoById(pedidoProdutoPK);
        pedidoProdutoDTO.ifPresent(produtoDTO -> dto.setQuantidade(dto.getQuantidade() + produtoDTO.getQuantidade()));
        return save(dto);
    }

    public Optional<PedidoProdutoDTO> findProdutoPedidoById(PedidoProdutoPK pedidoProdutoPK) {
        return pedidoProdutoRepository.findById(pedidoProdutoPK).map(pedidoProdutoMapper::toDto);
    }

    public PedidoProdutoDTO removerProduto(PedidoProdutoDTO dto) {
        Integer quantidadeAtual = pedidoProdutoRepository.
                findQuantidadeProdutoByIdPedidoAndIdProduto(dto.getIdPedido(), dto.getIdProduto());
        if (dto.getQuantidade() > quantidadeAtual) {
            throw new QuantidadeProdutoException("A quantidade passada é maior que a quantidade atual");
        }
        if (dto.getQuantidade().equals(quantidadeAtual)) {
            pedidoProdutoRepository.delete(pedidoProdutoMapper.toEntity(dto));
            return null;
        }
        return save(dto);
    }

    private PedidoProdutoDTO save(PedidoProdutoDTO dto) {
        return pedidoProdutoMapper.toDto(pedidoProdutoRepository.save(pedidoProdutoMapper.toEntity(dto)));
    }

    public BigDecimal calcularPrecoTotalById(Long id) {
        return pedidoProdutoRepository.calcularPrecoTotalPedidoById(id);
    }

    public Optional<PedidoDTO> fecharPedido(FecharPedidoDTO dto) {
        PedidoDTO pedidoDTO = findPedidoById(dto.getId())
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado"));

        if (pedidoDTO.getPedidoFechado().equals(Boolean.TRUE)){
            throw new PedidoFechadoException("O pedido já está fechado");
        }
        BigDecimal precoTotal = calcularPrecoTotalById(dto.getId());
        if (precoTotal.compareTo(dto.getValorPagamento()) > 0) {
            throw new PagamentoInsuficienteException("O valor do pagamento é inferior ao valor total do pedido");
        }

        fecharPedido(dto, pedidoDTO, precoTotal);
        salvar(pedidoDTO);

        return Optional.of(pedidoDTO);
    }

    private static void fecharPedido(FecharPedidoDTO dto, PedidoDTO pedidoDTO, BigDecimal precoTotal) {
        pedidoDTO.setTroco(dto.getValorPagamento().subtract(precoTotal));
        pedidoDTO.setValorPagamento(dto.getValorPagamento());
        pedidoDTO.setPedidoFechado(Boolean.TRUE);
    }

    public Optional<PedidoDTO> findPedidoById(Long id) {
        return pedidoRepository.findById(id).map(mapper::toDto);
    }

    public BigDecimal calcularPrecoTotal(TotalPedidoDTO dto) {
        BigDecimal precoTotal = BigDecimal.ZERO;
        for (ProdutoQuantidadeDTO produtoQuantidadeDTO : dto.getProdutoQuantidadeDTOList()) {
            BigDecimal precoProduto = produtoRepository.findPrecoByProdutoId(produtoQuantidadeDTO.getIdProduto());
            BigDecimal quantidade = BigDecimal.valueOf(produtoQuantidadeDTO.getQuantidade());
            precoTotal = precoTotal.add(precoProduto.multiply(quantidade));
        }
        return precoTotal;
    }

    public List<PedidoDTO> buscarTodos() {
        return mapper.toDto(repository.findAll());
    }

    public PedidoDTO buscarPorId(Long id) {
        return mapper.toDto(findById(id));
    }

    private Pedido findById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }


    public List<PedidoProdutoListDTO> buscarProdutosPedidosById(Long idPedido) {
        return repository.buscarProdutosPedidosById(idPedido);
    }
}