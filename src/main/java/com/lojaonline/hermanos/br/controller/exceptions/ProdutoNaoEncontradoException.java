package com.lojaonline.hermanos.br.controller.exceptions;

public class ProdutoNaoEncontradoException extends RuntimeException {
    private Long idProduto;

    public ProdutoNaoEncontradoException(Long idProduto) {
        this.idProduto = idProduto;
    }

    public Long getIdProduto() {
        return idProduto;
    }
}
