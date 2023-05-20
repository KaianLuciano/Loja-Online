package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import com.lojaonline.hermanos.br.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    final UsuarioRepository usuarioRepository;
    final PedidoRepository pedidoRepository;
    final ProdutoRepository produtoRepository;

    public List<UsuarioModel> findAll(){
        return usuarioRepository.findAll();
    }

    public UsuarioModel save(UsuarioModel usuario){
        List<PedidoModel> pedidos = usuario.getPedidos();

        usuarioRepository.save(usuario);

        for (PedidoModel pedido : pedidos) {
            pedido.setUsuario(usuario);
            pedidoRepository.save(pedido);
        }

        usuarioRepository.save(usuario);

        return usuario;
    }

    /*public UsuarioModel adicionarProdutoAoPedido(UsuarioModel usuario, ProdutoModel novoProduto) {
        List<PedidoModel> pedidos = usuario.getPedidos();

        if(pedidos.isEmpty()) {
            PedidoModel novoPedido = new PedidoModel();
            novoPedido.setUsuario(usuario);
            novoPedido.getProdutos().add(novoProduto);
        } else {
            PedidoModel pedidoExistente = pedidos.get(0);
            pedidoExistente.getProdutos().add(novoProduto);
        }

        usuario.setPedidos(pedidos);
        usuarioRepository.save(usuario);

        return usuario;
    }*/

    public Optional<UsuarioModel> findById(Long cpf){
        return usuarioRepository.findById(cpf);
    }

    public void delete(UsuarioModel usuarioModel){
        usuarioRepository.delete(usuarioModel);
    }

}
