package com.lojaonline.hermanos.br.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
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
    private List<ProdutoModel> produtos;
    private UsuarioModel usuario;

    public CarrinhoDTO(CarrinhoModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
