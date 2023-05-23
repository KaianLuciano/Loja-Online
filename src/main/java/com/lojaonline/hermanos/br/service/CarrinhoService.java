package com.lojaonline.hermanos.br.service;

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

    public List<CarrinhoModel> findAll(){
        return carrinhoRepository.findAll();
    }

    public Optional<CarrinhoModel> findById(Long id){return carrinhoRepository.findById(id);}

    public CarrinhoModel saveCarrinho (CarrinhoModel carrinhoModel) {
        carrinhoRepository.save(carrinhoModel);
        return carrinhoModel;
    }

    public void delete(CarrinhoModel carrinhoModel){
        carrinhoRepository.delete(carrinhoModel);
    }

}
