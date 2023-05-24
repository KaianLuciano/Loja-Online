package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.DTO.CarrinhoDTO;
import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.repository.CarrinhoRepository;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import com.lojaonline.hermanos.br.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CarrinhoService {

    final CarrinhoRepository carrinhoRepository;

    public List<CarrinhoDTO> findAll(){
        List<CarrinhoModel> carrinho = carrinhoRepository.findAll();
        return carrinho.stream().map(x -> new CarrinhoDTO(x)).toList();
    }

    public Optional<CarrinhoModel> findById(Long id){
       return carrinhoRepository.findById(id);
    }

    public CarrinhoModel saveCarrinho (CarrinhoModel carrinhoModel) {
        System.out.println("Aqui");
        return carrinhoRepository.save(carrinhoModel);
    }

    public CarrinhoModel delete(CarrinhoModel carrinhoModel){
        carrinhoRepository.delete(carrinhoModel);
        return carrinhoModel;
    }

}
