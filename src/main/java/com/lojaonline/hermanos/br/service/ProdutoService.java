package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProdutoService {

    final ProdutoRepository produtoRepository;

    final PedidoRepository pedidoRepository;

    public List<ProdutoModel> findAll(){
        return produtoRepository.findAll();
    }

    @Transactional
    public ProdutoModel save(ProdutoModel produtoModel){
        produtoRepository.save(produtoModel);
        return produtoModel;
    }

    public Optional<ProdutoModel> findById(Long id){
        return produtoRepository.findById(id);
    }

    public void delete(ProdutoModel produtoModel){
        produtoRepository.delete(produtoModel);
    }


}
