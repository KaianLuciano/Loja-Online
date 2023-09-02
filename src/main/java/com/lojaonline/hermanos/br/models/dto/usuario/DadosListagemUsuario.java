package com.lojaonline.hermanos.br.models.dto.usuario;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Usuario;

import java.util.List;

public record DadosListagemUsuario(String cpf, String nome, String email, List<Pedido> pedidos, Carrinho carrinho) {
    public DadosListagemUsuario(Usuario usuario) {
        this(
                usuario.getCpf(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getPedidos(),
                usuario.getCarrinho());
    }
}
