package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.controller.util.ControllerUtils;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/produtos")
@AllArgsConstructor
public class ProdutoController {

    final ProdutoService produtoService;

    final ControllerUtils controllerUtils;

    final PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<Object> findAll(){
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneProduto(@PathVariable(value = "id") Long id) {

        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(id);

        if(!produtoModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ProdutoModel produtoModels){

        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoModels));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletaProduto(@PathVariable(value = "id") Long id){
        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(id);

        if(!produtoModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        produtoService.delete(produtoModelsOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Produto Deletado");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduto(@PathVariable(value = "id") Long id, @RequestBody ProdutoModel produtoModel) {

        Optional<ProdutoModel> produtoModelsOptional = produtoService.findById(id);
        if(!produtoModelsOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não foi encontrado");
        }

        var produtoModelPut = produtoModelsOptional.get();
        produtoModelPut.setNome(produtoModel.getNome());
        produtoModelPut.setDescricao(produtoModel.getDescricao());
        produtoModelPut.setPreco(produtoModel.getPreco());
        produtoModelPut.setQtdDisponivel(produtoModel.getQtdDisponivel());
        produtoModelPut.setCategoria(produtoModelPut.getCategoria());

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoModelPut));
    }

    /*Irá receber uma requisição POST recebendo um objeto Json com o seguinte corpo
        "nome": "Iphone 13",
        "descricao": "Incrivel celular",
        "preco": 222.33,
        "qtdDisponivel": 1,
        "categoria": "Smarthphones",
        "pedido": "",
        "idPedido": 1,
        "idorodutos: [1,2,3]
     */
    @PutMapping("/{id}/associar-produtos")
    public ResponseEntity<Object> associarPedido(@PathVariable(value = "id") Long id,@RequestBody Map<String, List<Object>> request) {
        Optional<PedidoModel> pedido;

        if(id != null) {
           pedido = pedidoService.findById(id);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("idPedido é nullo ou não corresponde a um número");
        }

        List<Object> idProdutos = request.get("idProdutos");
        List<Integer> idProdutosAuxiliar = new ArrayList<>();

        for(Object teste : idProdutos) {
           idProdutosAuxiliar.add((Integer) teste);
        }

        List<Long> idDosProdutosRecebidos = new ArrayList<>();

        for(Integer sla : idProdutosAuxiliar) {
            idDosProdutosRecebidos.add(idProdutosAuxiliar != null ? sla.longValue() : null);
        }

        List<ProdutoModel> produtos = new ArrayList<>();

        for(Long idDosProdutos : idDosProdutosRecebidos) {
            produtos.add(produtoService.findById(idDosProdutos).get());
        }

        return ResponseEntity.status(HttpStatus.OK).body(produtoService.associarProdutoAoPedido(produtos, pedido.get()));
    }

}