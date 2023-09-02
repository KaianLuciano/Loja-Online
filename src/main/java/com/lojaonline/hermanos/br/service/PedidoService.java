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
import com.lojaonline.hermanos.br.repository.UsuarioRepository;
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
    private final UsuarioRepository usuarioRepository;
    private final PedidoUtils pedidoUtils;

    public List<DadosListagemPedido> findAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        return pedidos.stream().map(pedido -> (new DadosListagemPedido(pedido))).toList();
    }

    public DadosListagemPedido findById(Long id) {
        return new DadosListagemPedido(pedidoRepository.findById(id).get());
    }

    @Transactional
    public DadosListagemPedido criarPedido(String cpfUsuario, List<Long> idProdutos) {
        List<Produto> produtosEncontrados = idProdutos.stream().map(idProduto -> produtoRepository.findById(idProduto).get()).toList();
        Usuario usuarioEncontrado = usuarioRepository.findById(cpfUsuario).get();

        /*
        Irá ser estourar uma exceção personalizada
        for(int contador = 0; contador < produtosEncontrados.size(); contador++){
            if (pedidoUtils.verificarProdutoComID(usuario.getCarrinho().getProdutos(), produtosEncontrados.get(contador).getId()) == false) {
                return ("Produto com o nome "
                        + produtosEncontrados.get(contador).getNome()
                        + " e número de indentificação "
                        + produtosEncontrados.get(contador).getId()
                        + " não está presente no carrinho, logo não é possivel concluir o pedido");
            }
        }*/

        Pedido pedido = new Pedido(produtosEncontrados, usuarioEncontrado);
        pedidoRepository.save(pedido);

        Carrinho carrinho = usuarioEncontrado.getCarrinho();
        for(int contador = 0 ; contador < produtosEncontrados.size(); contador++) {
            if(pedidoUtils.verificarProdutoComID(carrinho.getProdutos(), produtosEncontrados.get(contador).getId())){
                carrinho.getProdutos().remove(produtosEncontrados.get(contador));
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
