package com.lojaonline.hermanos.br.models.dto.pedido;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Produto;
import com.lojaonline.hermanos.br.models.Usuario;
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
    private List<Produto> produtos;
    private Usuario usuario;
    private Status statusPedido;

    public PedidoDTO(Pedido entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
