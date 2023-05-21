package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import com.lojaonline.hermanos.br.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@AllArgsConstructor
public class UsuarioController {

    final UsuarioService usuarioService;

    final ControllerUtils controllerUtils;

    final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long cpf) {

        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findById(cpf);

        if(!usuarioModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(cpf));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UsuarioModel usuarioModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioModel));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Object> deletaUsuario(@PathVariable(value = "cpf") Long cpf){
        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findById(cpf);

        if(!usuarioModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        usuarioService.delete(usuarioModelsOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Produto Deletado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") Long cpf, @RequestBody UsuarioModel usuarioModel) {

        Optional<UsuarioModel> usuarioModelsOptional = usuarioService.findById(cpf);
        if(!usuarioModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        var usuarioModelPut = usuarioModelsOptional.get();
        usuarioModelPut.setNome(usuarioModel.getNome());
        usuarioModelPut.setEmail(usuarioModel.getEmail());
        usuarioModelPut.setSenha(usuarioModel.getSenha());
        usuarioModelPut.setCpf(usuarioModel.getCpf());
        usuarioModelPut.setPedidos(usuarioModel.getPedidos());

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuarioModelPut));
    }

}
