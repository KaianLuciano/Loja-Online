package com.lojaonline.hermanos.br.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lojaonline.hermanos.br.DTO.PedidoDTO;
import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.models.enums.Status;
import com.lojaonline.hermanos.br.models.utils.PedidoUtils;
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
    final PedidoUtils pedidoUtils;

    public List<PedidoModel> findAll(){
        return pedidoRepository.findAll();
    }

    public Optional<PedidoModel> findById(Long id) { return pedidoRepository.findById(id); }

    /* Ao criar o pedido ele transfere a lista de produtos do carrinho para o pedido,
        e logo em seguida ele limpa os pedidos do carrinho. */
    @Transactional
    public PedidoModel criarPedido(UsuarioModel usuario, List<ProdutoModel> produtos) {
        PedidoModel pedido = new PedidoModel();
        pedido.setProdutos(produtos);
        pedido.setUsuario(usuario);
        pedido.setStatusPedido(Status.PENDENTE);

        pedidoRepository.save(pedido);

        CarrinhoModel carrinho = usuario.getCarrinhoModel();

        for(int contador = 0 ; contador < produtos.size(); contador++) {
            if(pedidoUtils.verificarProdutoComID(carrinho.getProdutos(), produtos.get(contador).getId())){
                carrinho.getProdutos().remove(produtos.get(contador));
            }
        }

        carrinhoRepository.save(carrinho);

        return pedido;
    }

    public PedidoModel delete(Optional<PedidoModel> pedidoModel) {
        pedidoRepository.delete(pedidoModel.get());
        return pedidoModel.get();
    }

}
