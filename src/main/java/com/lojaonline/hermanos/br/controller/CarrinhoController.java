package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.service.CarrinhoService;
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
@RequestMapping("/api/carrinhos")
@AllArgsConstructor
public class CarrinhoController {

    final CarrinhoService carrinhoService;

    final ProdutoService produtoService;

    final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {

        Optional<CarrinhoModel> carrinhoModelOptional = carrinhoService.findById(id);

        if(!carrinhoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> saveCarrinho(@RequestBody CarrinhoModel carrinhoModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(carrinhoService.saveCarrinho(carrinhoModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCarrinho(@PathVariable(value = "id") Long id){
        Optional<CarrinhoModel> carrinhoModelOptional = carrinhoService.findById(id);

        if(!carrinhoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrinho não foi encontrado");
        }

        carrinhoService.delete(carrinhoModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Carrinho Deletado");
    }

    /*
        Corpo que será recebido no Json
        {
            "idProdutos": [1, 2, 3]",
            "idUsuario": 1
        }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Object> adicionarProdutoCarrinho(@PathVariable(value = "id") Long cpf, @RequestBody Map<String, Object> request) {
        List<Object> idProdutos = (List<Object>) request.get("idProdutos");
        String idUsuario = (String) request.get("idUsuario");
        List<Integer> idProdutosInteger = new ArrayList<>();

        for(Object idProdutosRecebidos : idProdutos) {
            idProdutosInteger.add((Integer) idProdutosRecebidos);
        }

        List<Long> idPedidosLong = new ArrayList<>();

        for(Integer idProdutosAuxiliar : idProdutosInteger) {
            idPedidosLong.add(idProdutosAuxiliar != null ? idProdutosAuxiliar.longValue() : null);
        }

        List<ProdutoModel> produtos = new ArrayList<>();

        for(Long idProdutoAuxiliar : idPedidosLong) {
            produtos.add(produtoService.findById(idProdutoAuxiliar).get());
        }

        UsuarioModel usuario = new UsuarioModel();

        if(idUsuario != null) {
            usuario = usuarioService.findById(idUsuario).get();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario correpondente ao id " + idUsuario + "não foi encontrado");
        }

        CarrinhoModel carrinho = new CarrinhoModel();

        carrinho.setProdutos(produtos);
        carrinho.setUsuario(usuario);

        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.saveCarrinho(carrinho));
    }

}
