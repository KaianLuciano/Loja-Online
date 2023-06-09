package com.lojaonline.hermanos.br.controller;


import com.lojaonline.hermanos.br.controller.exceptions.ProdutoNaoEncontradoException;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.models.utils.PedidoUtils;
import com.lojaonline.hermanos.br.service.PedidoService;
import com.lojaonline.hermanos.br.service.ProdutoService;
import com.lojaonline.hermanos.br.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/pedidos", produces = {"application/json"})
@Tag(name = "pedidos")
@AllArgsConstructor
public class PedidoController {

    final PedidoService pedidoService;

    final PedidoUtils pedidoUtils;

    final UsuarioService usuarioService;

    final ProdutoService produtoService;

    @Operation(summary = "Procura todos os pedidos do banco")
    @GetMapping
    public ResponseEntity<Object> findAll() {
        List<PedidoModel> pedidoModelList = pedidoService.findAll();
        if(pedidoModelList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Lista de pedidos vazia!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findAll());
    }

    @Operation(summary = "Procura no banco o pedido que representa o id passado")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.findById(id));
    }

    @Operation(summary = "Cria um pedido e exclui os itens do carrinho do usuario especificado")
    /* Irá receber no corpo do Json os produtos que serão feito os pedidos
        Json:
        {
            "idProdutos": [];
        } */
    @PostMapping(value = "/criar-pedido/{cpfUsuario}")
    public ResponseEntity<Object> criarPedido(@PathVariable(value = "cpfUsuario") String cpf, @RequestBody Map<String,Object> request) {
        UsuarioModel usuario = usuarioService.findByIdPrivate(cpf).get();
        List<Integer> idProdutosInteger = (List<Integer>) request.get("idProdutos");

        List<Long> idProdutosLong = new ArrayList<>();
        for(Integer idProdutosAuxiliar : idProdutosInteger) {
            idProdutosLong.add(idProdutosAuxiliar != null ? idProdutosAuxiliar.longValue() : null );
        }

        List<ProdutoModel> produtos = new ArrayList<>();
        for(Long idProdutosAuxiliar : idProdutosLong) {
            produtos.add(produtoService.findById(idProdutosAuxiliar).orElseThrow(() -> {throw new ProdutoNaoEncontradoException(idProdutosAuxiliar);}));
        }

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


    @Operation(summary = "Deleta um pedido")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePedido(@PathVariable(value = "id") Long id) {

        Optional<PedidoModel> pedidoModelOptional = pedidoService.findById(id);
        if(!pedidoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body("Pedido não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(pedidoService.delete(pedidoModelOptional));
    }

}
