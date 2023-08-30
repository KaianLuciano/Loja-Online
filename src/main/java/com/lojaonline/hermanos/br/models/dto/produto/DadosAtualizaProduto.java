package com.lojaonline.hermanos.br.models.dto.produto;

import com.lojaonline.hermanos.br.models.Produto;

public record DadosAtualizaProduto(String nome, String descricao, Double preco, Integer qtdDisponivel, String categoria) {

    public DadosAtualizaProduto(Produto produto) {
        this(
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQtdDisponivel(),
                produto.getCategoria()
        );
    }
}
