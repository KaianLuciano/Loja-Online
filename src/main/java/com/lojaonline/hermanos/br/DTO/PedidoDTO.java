package com.lojaonline.hermanos.br.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.models.enums.Status;
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
public class PedidoDTO implements Serializable {

    @JsonIgnore
    private Long id;
    private List<ProdutoModel> produtos;
    private UsuarioModel usuario;
    private Status statusPedido;

    public PedidoDTO(PedidoModel entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
