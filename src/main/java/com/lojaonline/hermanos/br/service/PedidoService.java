package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {

    final PedidoRepository pedidoRepository;

    public List<PedidoModel> findAll(){
        return pedidoRepository.findAll();
    }

    public Optional<PedidoModel> findById(Long id) { return pedidoRepository.findById(id); }

    public PedidoModel save(PedidoModel pedidoModel) {
        pedidoRepository.save(pedidoModel);
        return pedidoModel;
    }

    public PedidoModel delete(Optional<PedidoModel> pedidoModel) {
        pedidoRepository.delete(pedidoModel.get());
        return pedidoModel.get();
    }



}
