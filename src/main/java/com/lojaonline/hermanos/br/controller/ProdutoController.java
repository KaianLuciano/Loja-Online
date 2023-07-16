package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/produtos", produces = {"application/json"})
@Tag(name = "produtos")
@AllArgsConstructor
public class ProdutoController {

    final ProdutoService produtoService;

    final ControllerUtils controllerUtils;

    final PedidoService pedidoService;

    @Operation(summary = "Procura todos o produtos existentes no banco")
    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @Operation(summary = "Procura no banco o produto que representa o id passado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @Operation(summary = "Cria um novo produto")
    @PostMapping
    public ResponseEntity<Object> saveProduto(@RequestBody Produto produtoModels){

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveProduto(produtoModels));
    }

    @Operation(summary = "Remove o produto com o id especificado")
    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Object> deletaProduto(@PathVariable(value = "idProduto") Long idProduto){
        Produto produtoEncontrado = new Produto(produtoService.findById(idProduto));
        produtoService.delete(produtoEncontrado);
        return ResponseEntity.status(HttpStatus.OK).body("Produto Deletado");
    }

    @Operation(summary = "Atualiza o produto que representa o id passado")
    @PutMapping("/{idProduto}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "idProduto") Long idProduto, @RequestBody Produto produto) {
        Produto produtoEncontrado = new Produto(produtoService.findById(idProduto));
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.saveProduto(produtoEncontrado));
    }

}