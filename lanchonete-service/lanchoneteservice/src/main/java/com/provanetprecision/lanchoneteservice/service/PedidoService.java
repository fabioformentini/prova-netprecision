package com.provanetprecision.lanchoneteservice.service;

import com.provanetprecision.lanchoneteservice.controller.errors.PagamentoInsuficienteException;
import com.provanetprecision.lanchoneteservice.controller.errors.PedidoFechadoException;
import com.provanetprecision.lanchoneteservice.controller.errors.PedidoNotFoundException;
import com.provanetprecision.lanchoneteservice.controller.errors.QuantidadeProdutoException;
import com.provanetprecision.lanchoneteservice.repository.PedidoProdutoRepository;
import com.provanetprecision.lanchoneteservice.repository.PedidoRepository;
import com.provanetprecision.lanchoneteservice.service.dto.FecharPedidoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoDTO;
import com.provanetprecision.lanchoneteservice.service.mapper.PedidoMapper;
import com.provanetprecision.lanchoneteservice.service.mapper.PedidoProdutoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    public PedidoDTO salvar(PedidoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public PedidoProdutoDTO adicionarProduto(PedidoProdutoDTO dto) {
        return save(dto);
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

    public BigDecimal calcularPrecoTotal(Long id) {
        return pedidoProdutoRepository.calcularPrecoTotalPedidoById(id);
    }

    public Optional<PedidoDTO> fecharPedido(FecharPedidoDTO dto) {
        PedidoDTO pedidoDTO = findPedidoById(dto.getId())
                .orElseThrow(() -> new PedidoNotFoundException("Pedido não encontrado"));

        if (pedidoDTO.getPedidoFechado().equals(Boolean.TRUE)){
            throw new PedidoFechadoException("O pedido já está fechado");
        }
        BigDecimal precoTotal = calcularPrecoTotal(dto.getId());
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
        log.debug("Request to get PedidoIdMagento: {}", id);
        return pedidoRepository.findById(id).map(mapper::toDto);
    }


}