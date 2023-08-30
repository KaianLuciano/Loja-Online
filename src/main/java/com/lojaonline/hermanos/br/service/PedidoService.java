package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.models.Usuario;
import com.lojaonline.hermanos.br.models.dto.pedido.DadosListagemPedido;
import com.lojaonline.hermanos.br.models.enums.Status;
import com.lojaonline.hermanos.br.models.utils.PedidoUtils;
import com.lojaonline.hermanos.br.repository.CarrinhoRepository;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final PedidoUtils pedidoUtils;

    public List<DadosListagemPedido> findAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> (new DadosListagemPedido(pedido))).toList();
    }

    public DadosListagemPedido findById(Long id) {
        return new DadosListagemPedido(pedidoRepository.findById(id).get());
    }

    @Transactional
    public DadosListagemPedido criarPedido(Usuario usuario, List<Produto> produtos) {
        Pedido pedido = new Pedido();
        pedido.setProdutos(produtos);
        pedido.setUsuario(usuario);
        pedido.setStatusPedido(Status.PENDENTE);

        pedidoRepository.save(pedido);

        Carrinho carrinho = usuario.getCarrinho();

        for(int contador = 0 ; contador < produtos.size(); contador++) {
            if(pedidoUtils.verificarProdutoComID(carrinho.getProdutos(), produtos.get(contador).getId())){
                carrinho.getProdutos().remove(produtos.get(contador));
            }
        }

        carrinhoRepository.save(carrinho);

        return new DadosListagemPedido(pedido);
    }

    @Transactional
    public DadosListagemPedido delete(Long idPedido) {
        pedidoRepository.deleteById(idPedido);
        return new DadosListagemPedido(pedidoRepository.findById(idPedido).get());
    }

}
