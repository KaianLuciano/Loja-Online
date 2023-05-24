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

    @DeleteMapping("/deleta-produto-carrinho/{idProduto}/{idCarrinho}")
    public ResponseEntity<Object> deleteCarrinho(@PathVariable(value = "idProduto") Long idProduto, @PathVariable(value = "idCarrinho") Long idCarrinho){
        CarrinhoModel carrinhoModel = carrinhoService.findById(idCarrinho).get();


        for(int contador = 0 ; contador < carrinhoModel.getProdutos().size(); contador++) {
            if (carrinhoModel.getProdutos().get(contador).getId() == idProduto) {
                carrinhoModel.getProdutos().remove(contador);
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.delete(carrinhoModel));
        }

    /*
        Corpo que será recebido no Json
        {
            "idProdutos": [1, 2, 3]",
        }
     */
    @PutMapping("/adicionar-produto/{idCarrinho}")
    public ResponseEntity<Object> adicionarProdutoCarrinho(@PathVariable(value = "idCarrinho") Long idCarrinho, @RequestBody Map<String, Object> request) {
        List<Integer> idProdutos = (List<Integer>) request.get("idProdutos");
        List<Long> idProdutosLong = new ArrayList<>();

        for(Integer idProdutoAuxiliar : idProdutos) {
                idProdutosLong.add(idProdutoAuxiliar != null ? idProdutoAuxiliar.longValue() : null);
        }

        List<ProdutoModel> produtos = new ArrayList<>();

        for(Long idProdutosAuxiliar : idProdutosLong) {
            produtos.add(produtoService.findById(idProdutosAuxiliar).get());
        }

        CarrinhoModel carrinho = carrinhoService.findById(idCarrinho).get();

        carrinho.setProdutos(produtos);
        System.out.println("Aqui");
        return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.saveCarrinho(carrinho));
    }

}
