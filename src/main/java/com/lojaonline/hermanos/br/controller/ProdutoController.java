package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(value = "/api/produtos", produces = {"application/json"})
@Tag(name = "produto")
@AllArgsConstructor
public class ProdutoController {

    final ProdutoService produtoService;

    final ControllerUtils controllerUtils;

    final PedidoService pedidoService;

    @Operation(summary = "Procura todos o produtos existentes no banco", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @Operation(summary = "Procura no banco o produto que representa o id passado", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {

        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(id);

        if(!produtoModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @Operation(summary = "Cria um novo produto", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @PostMapping
    public ResponseEntity<Object> saveProduto(@RequestBody ProdutoModel produtoModels){

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.saveProduto(produtoModels));
    }

    @Operation(summary = "Remove o produto com o id especificado", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @DeleteMapping("/{idProduto}")
    public ResponseEntity<Object> deletaProduto(@PathVariable(value = "idProduto") Long idProduto){
        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(idProduto);

        if(!produtoModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        produtoService.delete(produtoModelsOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Produto Deletado");
    }

    @Operation(summary = "Atualiza o produto que representa o id passado", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @PutMapping("/{idProduto}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "idProduto") Long idProduto, @RequestBody ProdutoModel produtoModel) {

        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(idProduto);
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