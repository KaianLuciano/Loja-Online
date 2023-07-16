package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.models.Usuario;
import com.lojaonline.hermanos.br.models.enums.Status;
import com.lojaonline.hermanos.br.models.utils.PedidoUtils;
import com.lojaonline.hermanos.br.repository.CarrinhoRepository;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedidoService {

    final PedidoRepository pedidoRepository;
    final ProdutoRepository produtoRepository;
    final CarrinhoRepository carrinhoRepository;
    final PedidoUtils pedidoUtils;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> findById(Long id) { return pedidoRepository.findById(id); }

    @Transactional
    public Pedido criarPedido(Usuario usuario, List<Produto> produtos) {
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

        return pedido;
    }

    @Transactional
    public Pedido delete(Optional<Pedido> pedidoModel) {
        pedidoRepository.delete(pedidoModel.get());
        return pedidoModel.get();
    }

}
