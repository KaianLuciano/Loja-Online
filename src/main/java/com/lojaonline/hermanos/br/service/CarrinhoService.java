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
            Carrinho carrinhoSalvo = carrinhoRepository.save(carrinho);
            return new DadosListagemCarrinho(carrinhoSalvo);
        }

        Carrinho carrinhoAtualizado =  verificaSeProdutoTemNoCarrinho(carrinho, produto);
        Carrinho carrinhoSalvo = carrinhoRepository.save(carrinhoAtualizado);
        return new DadosListagemCarrinho(carrinhoSalvo);
    }

    private Carrinho verificaSeProdutoTemNoCarrinho(Carrinho carrinho, Produto produto) {
        for(int contador = 0 ; contador < carrinho.getProdutos().size() ; contador++) {
            if (carrinho.getProdutos().get(contador).getId().equals(produto.getId())) {
                carrinho.getProdutos().get(contador).setQuatidadeCarrinho(carrinho.getProdutos().get(contador).getQuatidadeCarrinho() + 1);
                return carrinho;
            }
        }
        carrinho.getProdutos().add(produto);
        return carrinho;
    }


}
