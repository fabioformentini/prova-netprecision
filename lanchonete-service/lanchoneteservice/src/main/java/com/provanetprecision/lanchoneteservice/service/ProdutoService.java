package com.provanetprecision.lanchoneteservice.service;

import com.provanetprecision.lanchoneteservice.repository.ProdutoRepository;
import com.provanetprecision.lanchoneteservice.service.dto.DropdownDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ProdutoService {

    private final ProdutoRepository repository;

    public List<DropdownDTO> buscarDropdown() {
        return repository.buscarDropdown();
    }
}