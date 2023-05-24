package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.DTO.UsuarioDTO;
import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.models.enums.Status;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import com.lojaonline.hermanos.br.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidoController {

    final PedidoService pedidoService;

    final ControllerUtils controllerUtils;

    final UsuarioService usuarioService;

    final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<PedidoModel> pedidoModelList = pedidoService.findAll();
        if(pedidoModelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de pedidos vazia!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findById(id));
    }

    @PostMapping("/criar-pedido/{cpfUsuario}")
    public ResponseEntity<Object> criarPedido(@PathVariable(value = "cpfUsuario") String cpf) {
        UsuarioModel usuario = usuarioService.findByIdPrivate(cpf).get();
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.criarPedido(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable(value = "id") Long id) {

        Optional<PedidoModel> pedidoModelOptional = pedidoService.findById(id);
        if(!pedidoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Pedido não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.delete(pedidoModelOptional));
    }

}
