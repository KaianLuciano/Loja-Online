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
@Tag(name = "usuarios")
@AllArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;
    final ProdutoService produtoService;

    @Operation(summary = "Procura todos os usuario do banco")
    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @Operation(summary = "Procura no banco o usuario que representa o id passado")
    @GetMapping(value = "/{cpfUsuario}")
    public ResponseEntity<Object> findById(@PathVariable(value = "cpfUsuario") String cpfUsuario) {
        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findByIdPrivate(cpfUsuario);

        if(usuarioModelsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(cpfUsuario));
    }

    @Operation(summary = "Cria um novo usuario")
    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuarioModel));
    }

    @Operation(summary = "Atualiza o usuario que representa o id passado")
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

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveUsuario(usuarioModelPut));
    }

}
