package com.lojaonline.hermanos.br.models.dto.produto;

import com.lojaonline.hermanos.br.models.Produto;

public record DadosListagemProduto(Long id, String nome, String descricao, Double preco, Integer qtdDisponivel, String categoria) {
    public DadosListagemProduto(Produto produto) {
        this(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getQtdDisponivel(),
                produto.getCategoria()
        );
    }
}
