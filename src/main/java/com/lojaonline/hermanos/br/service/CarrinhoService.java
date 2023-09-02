package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.models.dto.carrinho.DadosListagemCarrinho;
import com.lojaonline.hermanos.br.repository.CarrinhoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public List<DadosListagemCarrinho> findAll(){
        List<Carrinho> carrinhos = carrinhoRepository.findAll();
        return carrinhos.stream().map(carrinho -> new DadosListagemCarrinho(carrinho)).toList();
    }

    public DadosListagemCarrinho findById(Long id){
       return new DadosListagemCarrinho(carrinhoRepository.findById(id).get());
    }

    @Transactional
    public DadosListagemCarrinho saveCarrinho (Carrinho carrinho) {
        return new DadosListagemCarrinho(carrinhoRepository.save(carrinho));
    }

    public DadosListagemCarrinho deleteProdutoCarrinho(Long idCarrinho, Long idProduto) {
        Carrinho carrinhoCliente = carrinhoRepository.findById(idCarrinho).get();
        carrinhoCliente.getProdutos().removeIf(produtos -> produtos.getId() == idProduto);
        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinhoCliente);

        return new DadosListagemCarrinho(carrinhoSalvo);
    }

    public DadosListagemCarrinho addProdutoNoCarrinho(Long idCarrinho, Long idProduto) {
        Produto produto = produtoRepository.findById(idProduto).get();
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).get();

        if(carrinho.getProdutos() == null) {
            carrinho.setProdutos(List.of(produto));
        } else {
            carrinho.getProdutos().add(produto);
        }

        return new DadosListagemCarrinho(carrinho);
    }

}
