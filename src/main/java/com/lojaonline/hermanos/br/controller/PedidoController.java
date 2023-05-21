package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.models.enums.Status;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<Object> savePedido(@RequestBody PedidoModel pedidos) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.savePedido(pedidos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") Long id) {

        Optional<PedidoModel> pedidoModelOptional = pedidoService.findById(id);
        if(!pedidoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Pedido não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.delete(pedidoModelOptional));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePedido( @PathVariable(value = "id") Long id, @RequestBody PedidoModel pedidoModel) {
        Optional<PedidoModel> pedidoModelOptional = pedidoService.findById(id);
        if (!pedidoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Pedido não encontrado");
        }

        var pedidoModelPut = pedidoModelOptional.get();
        pedidoModelPut.setUsuario(pedidoModel.getUsuario());
        pedidoModelPut.setProdutos(pedidoModel.getProdutos());
        pedidoModelPut.setStatusPedido(pedidoModel.getStatusPedido());

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.savePedido(pedidoModelPut));
    }


    /*Irá receber uma requisição POST recebendo um objeto Json com o seguinte corpo
        "produtos": [],
        "usuario": "",
        "statusPedido": "0",
        "idUsuario": 1
     */
    @PutMapping("/{id}/associar-usuario")
    public ResponseEntity<Object> associarUsuario(@PathVariable(value = "id") Long id, @RequestBody Map<String, Object> request) {

        Integer idUsuarioInteger = (Integer) request.get("idUsuario");
        Long idUsuario = idUsuarioInteger != null ? idUsuarioInteger.longValue() : null;

        String statusPedido = (String) request.get("statusPedido");
        Optional<UsuarioModel> usuario;

        if(idUsuario != null) {
            usuario = usuarioService.findById(idUsuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("idUsuario é nullo ou não corresponde a um numero");
        }

        PedidoModel pedido = new PedidoModel();
        pedido.setProdutos((List<ProdutoModel>) request.get("produtos"));

        int statusPedidoValue = Integer.parseInt(statusPedido) + 1;
        if (Status.isValidValue(statusPedidoValue)) {
           Status status = Status.values()[statusPedidoValue];
           pedido.setStatusPedido(status);
        } else {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Status Inserido Invalido, " +  "enum inserido: " + statusPedidoValue);
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.associarPedidoAoUsuario(pedido, usuario.get()));

    }

}
