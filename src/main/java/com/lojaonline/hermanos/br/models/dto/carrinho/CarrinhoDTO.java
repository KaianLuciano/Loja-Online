package com.lojaonline.hermanos.br.models.dto.carrinho;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarrinhoDTO implements Serializable {

    private Long id;
    private List<Produto> produtos;

    public CarrinhoDTO(Carrinho entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
