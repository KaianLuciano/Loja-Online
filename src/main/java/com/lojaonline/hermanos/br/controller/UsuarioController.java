package com.lojaonline.hermanos.br.controller;


import com.lojaonline.hermanos.br.models.Usuario;
import com.lojaonline.hermanos.br.models.dto.usuario.DadosListagemUsuario;
import com.lojaonline.hermanos.br.repository.UsuarioRepository;
import com.lojaonline.hermanos.br.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Usuario")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Operation(summary = "Procura todos os usuario do banco")
    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @Operation(summary = "Procura no banco o usuario que representa o id passado")
    @GetMapping(value = "/{cpfUsuario}")
    public ResponseEntity<DadosListagemUsuario> findById(@PathVariable(value = "cpfUsuario") String cpfUsuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(cpfUsuario));
    }

    @Operation(summary = "Cria um novo usuario")
    @PostMapping
    public ResponseEntity<DadosListagemUsuario> saveUsuario(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.saveUsuario(usuario));
    }

    @Operation(summary = "Atualiza o usuario que representa o id passado")
    @PutMapping("/{cpfUsuario}")
    public ResponseEntity<DadosListagemUsuario> updateUsuario(@PathVariable(value = "cpfUsuario") String cpfUsuario, @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.updateUsuario(cpfUsuario, usuario));
    }

}
