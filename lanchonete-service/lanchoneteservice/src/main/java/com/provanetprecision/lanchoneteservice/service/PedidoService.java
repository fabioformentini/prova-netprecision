package com.provanetprecision.lanchoneteservice.service;

import com.provanetprecision.lanchoneteservice.repository.PedidoProdutoRepository;
import com.provanetprecision.lanchoneteservice.repository.PedidoRepository;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoDTO;
import com.provanetprecision.lanchoneteservice.service.dto.PedidoProdutoDTO;
import com.provanetprecision.lanchoneteservice.service.mapper.PedidoMapper;
import com.provanetprecision.lanchoneteservice.service.mapper.PedidoProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PedidoService{

    private final PedidoMapper mapper;
    private final PedidoProdutoMapper pedidoProdutoMapper;
    private final PedidoRepository repository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    public PedidoDTO salvar(PedidoDTO dto) {
        return mapper.toDto(repository.save(mapper.toEntity(dto)));
    }

    public PedidoProdutoDTO adicionarProduto(PedidoProdutoDTO dto) {
        return save(dto);
    }

    public PedidoProdutoDTO removerProduto(PedidoProdutoDTO dto) {
        Integer quantidadeAtual = pedidoProdutoRepository.
                findQuantidadeProdutoByIdPedidoAndIdProduto(dto.getIdPedido(), dto.getIdProduto());
        if (dto.getQuantidade() > quantidadeAtual){
            throw new RuntimeException("A quantidade passada é maior que a quantidade atual");
        }
        if (dto.getQuantidade().equals(quantidadeAtual)){
            pedidoProdutoRepository.delete(pedidoProdutoMapper.toEntity(dto));
            return null;
        }
        return save(dto);
    }

    private PedidoProdutoDTO save(PedidoProdutoDTO dto){
        return pedidoProdutoMapper.toDto(pedidoProdutoRepository.save(pedidoProdutoMapper.toEntity(dto)));
    }

}