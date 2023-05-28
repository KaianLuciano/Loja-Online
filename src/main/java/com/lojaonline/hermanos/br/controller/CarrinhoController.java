package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.service.CarrinhoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import com.lojaonline.hermanos.br.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
@Tag(name = "loja-online")
@AllArgsConstructor
public class CarrinhoController {

    final CarrinhoService carrinhoService;

    final ProdutoService produtoService;

    final UsuarioService usuarioService;


    @Operation(summary = "Buscar todas os carrinhos presentes no banco", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findAll());
    }

    @Operation(summary = "Buscar todas o carrinho que representa o id passado no banco", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {

        Optional<CarrinhoModel> carrinhoModelOptional = carrinhoService.findById(id);

        if(!carrinhoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findById(id));
    }

    @Operation(summary = "Adiciona um produto no carrinho", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    /*
        Corpo que será recebido no Json
        {
            "idProdutos": [1, 2, 3]",
        }
     */
    @PutMapping(value = "/adicionar-produto/{idCarrinho}")
    public ResponseEntity<Object> adicionarProdutoCarrinho(@PathVariable(value = "idCarrinho") Long idCarrinho, @RequestBody Map<String, Object> request) {
        List<Integer> idProdutos = (List<Integer>) request.get("idProdutos");
        List<Long> idProdutosLong = new ArrayList<>();

        for(Integer idProdutoAuxiliar : idProdutos) {
                idProdutosLong.add(idProdutoAuxiliar != null ? idProdutoAuxiliar.longValue() : null);
        }

        List<ProdutoModel> produtos = new ArrayList<>();

        for(Long idProdutosAuxiliar : idProdutosLong) {
            produtos.add(produtoService.findById(idProdutosAuxiliar).get());
        }

        CarrinhoModel carrinho = carrinhoService.findById(idCarrinho).get();

        carrinho.setProdutos(produtos);
        System.out.println("Aqui");
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.saveCarrinho(carrinho));
    }

}
