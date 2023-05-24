package com.lojaonline.hermanos.br.controller;


import com.lojaonline.hermanos.br.controller.exceptions.ProdutoNaoEncontradoException;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.models.utils.PedidoUtils;
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

    final PedidoUtils pedidoUtils;

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

    /* Irá receber no corpo do Json os produtos que serão feito os pedidos
        Json:
        {
            "idProdutos": [];
        } */
    @PostMapping("/criar-pedido/{cpfUsuario}")
    public ResponseEntity<Object> criarPedido(@PathVariable(value = "cpfUsuario") String cpf, @RequestBody Map<String,Object> request) {
        UsuarioModel usuario = usuarioService.findByIdPrivate(cpf).get();
        List<Integer> idProdutosInteger = (List<Integer>) request.get("idProdutos");

        //Valida se os idProdutos possui algum valor atrelado ou é null
        List<Long> idProdutosLong = new ArrayList<>();
        for(Integer idProdutosAuxiliar : idProdutosInteger) {
            idProdutosLong.add(idProdutosAuxiliar != null ? idProdutosAuxiliar.longValue() : null );
        }

        //Verifica se o produto existe no banco, logo em seguida adiciona eles a lista de produtos caso exista
        List<ProdutoModel> produtos = new ArrayList<>();
        for(Long idProdutosAuxiliar : idProdutosLong) {
            produtos.add(produtoService.findById(idProdutosAuxiliar).orElseThrow(() -> {throw new ProdutoNaoEncontradoException(idProdutosAuxiliar);}));
        }

        //Verifica se o usuário possui estes produtos no carrinho
        for(int contador = 0; contador < produtos.size(); contador++){
            if (pedidoUtils.verificarProdutoComID(usuario.getCarrinhoModel().getProdutos(), produtos.get(contador).getId()) == false) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto com o nome "
                        + produtos.get(contador).getNome()
                        + " e número de indentificação "
                        + produtos.get(contador).getId()
                        + " não está presente no carrinho, logo não é possivel concluir o pedido");
            }
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.criarPedido(usuario, produtos));
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
