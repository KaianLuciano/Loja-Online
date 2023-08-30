package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.repository.CarrinhoRepository;
import com.lojaonline.hermanos.br.service.CarrinhoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import com.lojaonline.hermanos.br.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/carrinhos", produces = {"application/json"})
@Tag(name = "carrinhos")
@AllArgsConstructor
public class CarrinhoController {

    final CarrinhoService carrinhoService;

    final ProdutoService produtoService;

    final UsuarioService usuarioService;
    final CarrinhoRepository carrinhoRepository;


    @Operation(summary = "Buscar todas os carrinhos presentes no banco")
    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findAll());
    }

    @Operation(summary = "Buscar todas o carrinho que representa o id passado no banco")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long idCarrinho) {
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findById(idCarrinho));
    }

    @Operation(summary = "Deleta um produto do carrinho")
    @DeleteMapping("/deleta-produto-carrinho/{idProduto}/{idCarrinho}")
    public ResponseEntity<Object> deleteCarrinho(@PathVariable(value = "idProduto") Long idProduto, @PathVariable(value = "idCarrinho") Long idCarrinho){
        //refatorar método
        return ResponseEntity.status(HttpStatus.OK).body("Produtos Deletado");
    }

    @Operation(summary = "Adiciona um produto ao carrinho")
    /*
        Corpo que será recebido no Json
        {
            "idProdutos": [1, 2, 3]",
        }
     */
    @PutMapping("/adicionar-produto/{idCarrinho}/{idProduto}")
    public ResponseEntity<Object> adicionarProdutoCarrinho(@PathVariable(value = "idCarrinho") Long idCarrinho, @PathVariable(value = "idProduto") Long idProduto) {
        Produto produto = new Produto(produtoService.findById(idProduto));
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho).get();

        if(carrinho.getProdutos() == null) {
            carrinho.setProdutos(List.of(produto));
        } else {
            carrinho.getProdutos().add(produto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.saveCarrinho(carrinho));
    }

}
