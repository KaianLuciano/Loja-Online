package com.lojaonline.hermanos.br.controller;


import com.lojaonline.hermanos.br.models.Usuario;
import com.lojaonline.hermanos.br.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Procura todos os usuario do banco")
    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @Operation(summary = "Procura no banco o usuario que representa o id passado")
    @GetMapping(value = "/{cpfUsuario}")
    public ResponseEntity<Object> findById(@PathVariable(value = "cpfUsuario") String cpfUsuario) {
        Optional<Usuario> usuarioModelsOptional = usuarioService.findByIdPrivate(cpfUsuario);

        if(usuarioModelsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(cpfUsuario));
    }

    @Operation(summary = "Cria um novo usuario")
    @PostMapping
    public ResponseEntity<Object> saveUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
    }

    @Operation(summary = "Atualiza o usuario que representa o id passado")
    @PutMapping("/{cpfUsuario}")
    public ResponseEntity<Object> updateUsuario(@PathVariable(value = "cpfUsuario") String cpfUsuario, @RequestBody Usuario usuario) {

        Optional<Usuario> usuarioModelsOptional = usuarioService.findByIdPrivate(cpfUsuario);
        if(usuarioModelsOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não foi encontrado");
        }

        var usuarioModelPut = usuarioModelsOptional.get();
        usuarioModelPut.setNome(usuario.getNome());
        usuarioModelPut.setEmail(usuario.getEmail());
        usuarioModelPut.setSenha(usuario.getSenha());

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.saveUsuario(usuarioModelPut));
    }

}
