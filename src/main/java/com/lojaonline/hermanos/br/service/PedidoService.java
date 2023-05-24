package com.lojaonline.hermanos.br.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lojaonline.hermanos.br.DTO.PedidoDTO;
import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.models.enums.Status;
import com.lojaonline.hermanos.br.repository.CarrinhoRepository;
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
    final CarrinhoRepository carrinhoRepository;

    public List<PedidoModel> findAll(){
        return pedidoRepository.findAll();
    }

    public Optional<PedidoModel> findById(Long id) { return pedidoRepository.findById(id); }

    /*
        Ao criar o pedido ele transfere a lista de produtos do carrinho para o pedido,
        e logo em seguida ele limpa os pedidos do carrinho.
     */
    @Transactional
    public PedidoModel criarPedido(UsuarioModel usuario) {
        PedidoModel pedido = new PedidoModel();
        pedido.setProdutos(usuario.getCarrinhoModel().getProdutos());
        pedido.setUsuario(usuario);
        pedido.setStatusPedido(Status.PENDENTE);

        pedidoRepository.save(pedido);

        CarrinhoModel carrinho = new CarrinhoModel();
        carrinho = usuario.getCarrinhoModel();
        carrinho.setProdutos(null);
        carrinhoRepository.save(carrinho);

        return pedido;
    }

    public PedidoModel delete(Optional<PedidoModel> pedidoModel) {
        pedidoRepository.delete(pedidoModel.get());
        return pedidoModel.get();
    }

}
