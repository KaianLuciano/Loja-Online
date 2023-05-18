package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.ProdutoModels;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {

    final ProdutoRepository produtoRepository;

    public List<ProdutoModels> findAll(){
        return produtoRepository.findAll();
    }

    public ProdutoModels save(ProdutoModels produtoModels){
        produtoRepository.save(produtoModels);
        return produtoModels;
    }

    public Optional<ProdutoModels> findById(Long id){
        return produtoRepository.findById(id);
    }

    public void delete(ProdutoModels produtoModels){
        produtoRepository.delete(produtoModels);
    }


}
