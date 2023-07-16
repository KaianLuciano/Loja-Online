package com.lojaonline.hermanos.br.models.dto.produto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Pedido;
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
public class ProdutoDTO implements Serializable {

    @JsonIgnore
    private Long id;
    private String nome;
    private String descricao;
    private Double preco;
    private Integer qtdDisponivel;
    private String categoria;
    @JsonIgnore
    List<Carrinho> carrinhos;
    @JsonIgnore
    private List<Pedido> pedido;

    public ProdutoDTO(Produto entity) {
        BeanUtils.copyProperties(entity, this);
    }

}
