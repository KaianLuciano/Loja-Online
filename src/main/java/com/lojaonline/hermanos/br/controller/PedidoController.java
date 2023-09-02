package com.lojaonline.hermanos.br.controller;


import com.lojaonline.hermanos.br.models.utils.PedidoUtils;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import com.lojaonline.hermanos.br.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
@Tag(name = "Pedido")
@AllArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;
    private final PedidoUtils pedidoUtils;
    private final UsuarioService usuarioService;
    private final ProdutoService produtoService;

    @Operation(summary = "Procura todos os pedidos do banco")
    @GetMapping
    public ResponseEntity<Object> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    @Operation(summary = "Procura no banco o pedido que representa o id passado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findById(id));
    }

    @Operation(summary = "Cria um pedido e exclui os itens do carrinho do usuario especificado")
    @PostMapping(value = "/criar-pedido/{cpfUsuario}")
    public ResponseEntity<Object> criarPedido(@PathVariable(value = "cpfUsuario") String cpfUsuario, @RequestBody List<Long> idProdutos) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.criarPedido(cpfUsuario, idProdutos));
    }


    @Operation(summary = "Deleta um pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable(value = "id") Long idPedido) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.delete(idPedido));
    }

}
