package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.dto.carrinho.DadosListagemCarrinho;
import com.lojaonline.hermanos.br.repository.CarrinhoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;

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

}
