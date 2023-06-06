package com.lojaonline.hermanos.br.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_produtos")
@Getter @Setter
public class ProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    private String nome;

    private String descricao;

    private Double preco;

    private String url_imagem;

    private Integer qtdDisponivel;

    private String categoria;

    @ManyToMany(mappedBy = "produtos")
    @JsonIgnore
    List<CarrinhoModel> carrinhos;

    @ManyToMany(mappedBy = "produtos")
    @JsonIgnore
    private List<PedidoModel> pedido;

}
