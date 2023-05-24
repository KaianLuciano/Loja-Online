package com.lojaonline.hermanos.br.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.ProdutoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import jakarta.persistence.*;
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
public class ProdutoDTO {

    @JsonIgnore
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer qtdDisponivel;
    private String categoria;
    @JsonIgnore
    List<CarrinhoModel> carrinhos;
    @JsonIgnore
    private List<PedidoModel> pedido;

    public ProdutoDTO(ProdutoModel entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
