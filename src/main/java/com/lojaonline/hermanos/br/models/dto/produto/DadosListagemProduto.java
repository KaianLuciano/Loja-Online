package com.lojaonline.hermanos.br.models.dto.produto;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Produto;

import java.util.List;

public record DadosListagemProduto( Long id, String nome, String descricao, Double preco, Integer qtdDisponivel, String categoria, List<Carrinho> carrinhos, List<Pedido> pedidos) {
    public DadosListagemProduto(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQtdDisponivel(),
                produto.getCategoria(),
                produto.getCarrinhos(),
                produto.getPedido()
        );
    }
}
