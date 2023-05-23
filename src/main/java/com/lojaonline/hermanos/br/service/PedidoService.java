package com.lojaonline.hermanos.br.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.models.enums.Status;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import com.lojaonline.hermanos.br.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {

    final PedidoRepository pedidoRepository;

    final ProdutoRepository produtoRepository;

    public List<PedidoModel> findAll(){
        return pedidoRepository.findAll();
    }

    public Optional<PedidoModel> findById(Long id) { return pedidoRepository.findById(id); }

    @Transactional
    public PedidoModel savePedido(PedidoModel pedido) {
        return pedidoRepository.save(pedido);
    }

    public PedidoModel associarPedidoAoUsuario(PedidoModel pedido, UsuarioModel usuario) {
        pedido.setUsuario(usuario);

        pedidoRepository.save(pedido);

        return pedido;
    }

    public PedidoModel delete(Optional<PedidoModel> pedidoModel) {
        pedidoRepository.delete(pedidoModel.get());
        return pedidoModel.get();
    }

}
