package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.dto.carrinho.CarrinhoDTO;
import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.repository.CarrinhoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarrinhoService {

    final CarrinhoRepository carrinhoRepository;

    public List<CarrinhoDTO> findAll(){
        List<Carrinho> carrinho = carrinhoRepository.findAll();
        return carrinho.stream().map(x -> new CarrinhoDTO(x)).toList();
    }

    public Optional<Carrinho> findById(Long id){
       return carrinhoRepository.findById(id);
    }

    @Transactional
    public Carrinho saveCarrinho (Carrinho carrinho) {
        System.out.println("Aqui");
        return carrinhoRepository.save(carrinho);
    }

}
