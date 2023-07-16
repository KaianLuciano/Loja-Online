package com.lojaonline.hermanos.br.models.dto.pedido;

import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.models.Usuario;
import com.lojaonline.hermanos.br.models.enums.Status;

import java.util.List;

public record DadosListagemPedido( Long id, List<Produto>produtos, Usuario usuario, Status statusPedido) {
    public DadosListagemPedido(Pedido pedido) {
        this(
                pedido.getId(),
                pedido.getProdutos(),
                pedido.getUsuario(),
                pedido.getStatusPedido());
    }
}
