package com.lojaonline.hermanos.br.models.utils;

import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PedidoUtils {

    final ProdutoRepository produtoRepository;

    public boolean verificarProdutoComID(List<Produto> listaProdutos, Long idProcurado){
        for (Produto produto : listaProdutos) {
            if (produto.getId() == idProcurado) {
                return true;
            }
        }
        return false;
    }

}
