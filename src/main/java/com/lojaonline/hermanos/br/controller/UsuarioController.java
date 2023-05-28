package com.lojaonline.hermanos.br.controller;


import com.lojaonline.hermanos.br.models.UsuarioModel;

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

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/usuarios", produces = {"application/json"})
@Tag(name = "usuário")
@AllArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;
    final ProdutoService produtoService;

    @Operation(summary = "Procura todos os usuario do banco", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @Operation(summary = "Procura no banco o usuario que representa o id passado", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @GetMapping(value = "/{cpfUsuario}")
    public ResponseEntity<Object> findById(@PathVariable(value = "cpfUsuario") String cpfUsuario) {
        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findByIdPrivate(cpfUsuario);

        if(usuarioModelsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(cpfUsuario));
    }

    @Operation(summary = "Cria um novo usuario", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuarioModel));
    }

    @Operation(summary = "Deleta um usuario", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @DeleteMapping("/{cpfUsuario}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "cpfUsuario") String cpfUsuario){
        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findByIdPrivate(cpfUsuario);

        if(usuarioModelsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado: " + usuarioService.delete(usuarioModelsOptional.get()));
    }

    @Operation(summary = "Atualiza o usuario que representa o id passado", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidados"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca dos dados"),
    })
    @PutMapping("/{cpfUsuario}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "cpfUsuario") String cpfUsuario, @RequestBody UsuarioModel usuarioModel) {

        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findByIdPrivate(cpfUsuario);
        if(usuarioModelsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não foi encontrado");
        }

        var usuarioModelPut = usuarioModelsOptional.get();
        usuarioModelPut.setNome(usuarioModel.getNome());
        usuarioModelPut.setEmail(usuarioModel.getEmail());
        usuarioModelPut.setSenha(usuarioModel.getSenha());
        usuarioModelPut.setCpf(usuarioModel.getCpf());
        usuarioModelPut.setPedidos(usuarioModel.getPedidos());

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveUsuario(usuarioModelPut));
    }

}
