package com.lojaonline.hermanos.br.models.utils;

import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PedidoUtils {

    final ProdutoRepository produtoRepository;

    public boolean verificarProdutoComID(List<ProdutoModel> listaProdutos, Long idProcurado){
        for (ProdutoModel produto : listaProdutos) {
            if (produto.getId() == idProcurado) {
                return true;
            }
        }
        return false;
    }

}
