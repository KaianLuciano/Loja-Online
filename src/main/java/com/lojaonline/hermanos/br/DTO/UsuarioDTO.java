package com.lojaonline.hermanos.br.DTO;

import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String email;
    private List<PedidoModel> pedidos;
    private CarrinhoModel carrinho;

    public UsuarioDTO(UsuarioModel entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
