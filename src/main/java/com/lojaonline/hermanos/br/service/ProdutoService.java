package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosAtualizaProduto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosCriaProduto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosListagemProduto;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final PedidoRepository pedidoRepository;

    public List<DadosListagemProduto> findAll(){
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream().map(produto -> (new DadosListagemProduto(produto))).toList();
    }

    public DadosListagemProduto findById(Long id){
        return new DadosListagemProduto(produtoRepository.findById(id).get());
    }


    @Transactional
    public DadosListagemProduto saveProduto(DadosCriaProduto dadosCriaProduto){
        Produto produto = new Produto(dadosCriaProduto);
        produtoRepository.save(produto);
        return new DadosListagemProduto(produto);
    }

    public DadosListagemProduto updateProduto(Long idProduto, DadosAtualizaProduto produto) {
        Produto produtoEncontrado = produtoRepository.findById(idProduto).get();
        Produto produtoAtualizado = new Produto(produtoEncontrado, produto);
        Produto produtoSalvo = produtoRepository.save(produtoAtualizado);

        return new DadosListagemProduto(produtoSalvo);
    }

    @Transactional
    public Produto delete(Long idProduto){
        Produto produtoEncontrado = produtoRepository.findById(idProduto).get();
        produtoRepository.delete(produtoEncontrado);
        return produtoEncontrado;
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