package com.lojaonline.hermanos.br.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.dto.produto.DadosListagemProduto;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_produtos")
@Getter @Setter
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Hidden
    private Long id;

    private String nome;

    private String descricao;

    private Double preco;

    private Integer qtdDisponivel;

    private String categoria;

    @ManyToMany(mappedBy = "produtos")
    @JsonIgnore
    List<Carrinho> carrinhos;

    @ManyToMany(mappedBy = "produtos")
    @JsonIgnore
    private List<Pedido> pedido;

    public Produto(DadosListagemProduto dadosListagemProduto) {
        this.id = dadosListagemProduto.id();
        this.nome = dadosListagemProduto.nome();
        this.descricao = dadosListagemProduto.descricao();
        this.preco = dadosListagemProduto.preco();
        this.qtdDisponivel = dadosListagemProduto.qtdDisponivel();
        this.categoria = dadosListagemProduto.categoria();
        this.carrinhos = dadosListagemProduto.carrinhos();
        this.pedido = dadosListagemProduto.pedidos();
    }
}
