package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosAtualizaProduto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosCriaProduto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosListagemProduto;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
@Tag(name = "Produto")
public class ProdutoController {

    final ProdutoService produtoService;
    final PedidoService pedidoService;

    @Operation(summary = "Procura todos o produtos existentes no banco")
    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @Operation(summary = "Procura no banco o produto que representa o id passado")
    @GetMapping(value = "/{idProduto}")
    public ResponseEntity<Object> findById(@PathVariable(value = "idProduto") Long idProduto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(idProduto));
    }

    @Operation(summary = "Cria um novo produto")
    @PostMapping
    public ResponseEntity<DadosListagemProduto> saveProduto(@RequestBody DadosCriaProduto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveProduto(produto));
    }

    @Operation(summary = "Remove o produto com o id especificado")
    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Produto> deletaProduto(@PathVariable(value = "idProduto") Long idProduto){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.delete(idProduto));
    }

    @Operation(summary = "Atualiza o produto que representa o id passado")
    @PutMapping("/{idProduto}")
    public ResponseEntity<DadosListagemProduto> updateProduto(@PathVariable(value = "idProduto") Long idProduto, @RequestBody DadosAtualizaProduto produto) {
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.updateProduto(idProduto, produto));
    }

}