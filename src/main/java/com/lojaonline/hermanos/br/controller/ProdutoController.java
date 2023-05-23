package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProdutoController {

    final ProdutoService produtoService;

    final ControllerUtils controllerUtils;

    final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {

        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(id);

        if(!produtoModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> saveProduto(@RequestBody ProdutoModel produtoModels){

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveProduto(produtoModels));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletaProduto(@PathVariable(value = "id") Long id){
        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(id);

        if(!produtoModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        produtoService.delete(produtoModelsOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Produto Deletado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") Long id, @RequestBody ProdutoModel produtoModel) {

        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(id);
        if(!produtoModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        var produtoModelPut = produtoModelsOptional.get();
        produtoModelPut.setNome(produtoModel.getNome());
        produtoModelPut.setDescricao(produtoModel.getDescricao());
        produtoModelPut.setPreco(produtoModel.getPreco());
        produtoModelPut.setQtdDisponivel(produtoModel.getQtdDisponivel());
        produtoModelPut.setCategoria(produtoModelPut.getCategoria());

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.saveProduto(produtoModelPut));
    }

}