package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosListagemProduto;
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

    public List<DadosListagemProduto> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> (new DadosListagemProduto(produto))).toList();
    }

    public Optional<Produto> findById(Long id){
        return produtoRepository.findById(id);
    }


    @Transactional
    public Produto saveProduto(Produto produto){
        produtoRepository.save(produto);
        return produto;
    }

    @Transactional
    public void delete(Produto produto){
        produtoRepository.delete(produto);
    }

    @Transactional
    public Pedido associarProdutoAoPedido(List<Produto> produtos, Pedido pedido) {
        pedido.setProdutos(produtos);
        pedidoRepository.save(pedido);

        for(Produto produto : produtos) {
            produto.getPedido().add(pedido);
            produtoRepository.save(produto);
        }

        return pedido;
    }


}