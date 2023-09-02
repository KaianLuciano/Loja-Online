package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.models.dto.carrinho.DadosListagemCarrinho;
import com.lojaonline.hermanos.br.service.CarrinhoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrinhos")
@Tag(name = "Carrinho")
@AllArgsConstructor
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @Operation(summary = "Buscar todas os carrinhos presentes no banco")
    @GetMapping
    public ResponseEntity<List<DadosListagemCarrinho>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findAll());
    }

    @Operation(summary = "Buscar todas o carrinho que representa o id passado no banco")
    @GetMapping(value = "/{id}")
    public ResponseEntity<DadosListagemCarrinho> findById(@PathVariable(value = "id") Long idCarrinho) {
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findById(idCarrinho));
    }

    @Operation(summary = "Deleta um produto do carrinho")
    @DeleteMapping("/deleta-produto-carrinho/{idProduto}/{idCarrinho}")
    public ResponseEntity<DadosListagemCarrinho> deleteCarrinho(@PathVariable(value = "idProduto") Long idProduto, @PathVariable(value = "idCarrinho") Long idCarrinho){
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.deleteProdutoCarrinho(idCarrinho, idProduto));
    }

    @Operation(summary = "Adiciona um produto ao carrinho")
    @PutMapping("/adicionar-produto/{idCarrinho}/{idProduto}")
    public ResponseEntity<DadosListagemCarrinho> adicionarProdutoCarrinho(@PathVariable(value = "idCarrinho") Long idCarrinho, @PathVariable(value = "idProduto") Long idProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.addProdutoNoCarrinho(idCarrinho, idProduto));
    }

}
