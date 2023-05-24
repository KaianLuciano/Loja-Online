package com.lojaonline.hermanos.br.controller;


import com.lojaonline.hermanos.br.models.UsuarioModel;

import com.lojaonline.hermanos.br.service.ProdutoService;
import com.lojaonline.hermanos.br.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;
    final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") String cpf) {
        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findByIdPrivate(cpf);

        if(usuarioModelsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(cpf));
    }

    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuarioModel));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object> deleteUsuario(@PathVariable(value = "cpf") String cpf){
        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findByIdPrivate(cpf);

        if(usuarioModelsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Produto deletado: " + usuarioService.delete(usuarioModelsOptional.get()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "id") String cpf, @RequestBody UsuarioModel usuarioModel) {

        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findByIdPrivate(cpf);
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
