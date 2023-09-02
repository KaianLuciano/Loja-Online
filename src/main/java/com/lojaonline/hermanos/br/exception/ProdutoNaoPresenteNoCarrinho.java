package com.lojaonline.hermanos.br.exception;

public class ProdutoNaoPresenteNoCarrinho extends RuntimeException{
    public ProdutoNaoPresenteNoCarrinho(String mensagem) {
        super(mensagem);
    }
}
