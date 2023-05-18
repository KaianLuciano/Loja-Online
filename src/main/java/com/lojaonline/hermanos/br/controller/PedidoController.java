package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.service.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pedidos")
@AllArgsConstructor
public class PedidoController {

    final PedidoService pedidoService;

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
    public ResponseEntity<Object> save(@RequestBody PedidoModel pedidoModel) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedidoModel));
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
        pedidoModelPut.setStatusPedido(pedidoModel.getStatusPedido());

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.save(pedidoModelPut));
    }

}
