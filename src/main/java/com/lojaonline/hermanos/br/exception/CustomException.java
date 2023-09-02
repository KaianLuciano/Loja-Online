package com.lojaonline.hermanos.br.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomException {
    @ExceptionHandler(ProdutoNaoPresenteNoCarrinho.class)
    public ResponseEntity<String> handleProdutoNaoPresenteNoCarrinho(ProdutoNaoPresenteNoCarrinho excecao) {
        String mensagemDeErro = excecao.getMessage();
        return new ResponseEntity<>(mensagemDeErro, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
