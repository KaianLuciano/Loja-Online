package com.lojaonline.hermanos.br.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.dto.produto.DadosAtualizaProduto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosCriaProduto;
import com.lojaonline.hermanos.br.models.dto.produto.DadosListagemProduto;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_produtos")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String descricao;

    private Double preco;

    private Integer qtdDisponivel;

    private String categoria;

    @ManyToMany(mappedBy = "produtos", cascade = CascadeType.ALL)
    @JsonIgnore
    List<Carrinho> carrinhos;

    @ManyToMany(mappedBy = "produtos", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Pedido> pedido;

    public Produto(DadosListagemProduto dadosListagemProduto) {
        this.id = dadosListagemProduto.id();
        this.nome = dadosListagemProduto.nome();
        this.descricao = dadosListagemProduto.descricao();
        this.preco = dadosListagemProduto.preco();
        this.qtdDisponivel = dadosListagemProduto.qtdDisponivel();
        this.categoria = dadosListagemProduto.categoria();
    }

    public Produto(Produto produtoEncontrado, DadosAtualizaProduto produto) {
        this.id = produtoEncontrado.getId();
        this.nome = produto.nome() != null ? produto.nome() : produtoEncontrado.getNome();
        this.descricao = produto.descricao() != null ? produto.descricao() : produtoEncontrado.getDescricao();
        this.preco = produto.preco() != null ? produto.preco() : produtoEncontrado.getPreco();
        this.qtdDisponivel = produto.qtdDisponivel() != null ? produto.qtdDisponivel() : produtoEncontrado.getQtdDisponivel();
        this.categoria = produto.categoria() != null ? produto.categoria() : produtoEncontrado.getCategoria();
        this.carrinhos = produtoEncontrado.getCarrinhos();
        this.pedido = produtoEncontrado.getPedido();
    }

    public Produto(DadosCriaProduto dadosCriaProduto) {
        this.nome = dadosCriaProduto.nome();
        this.descricao = dadosCriaProduto.descricao();
        this.preco = dadosCriaProduto.preco();
        this.qtdDisponivel = dadosCriaProduto.qtdDisponivel();
        this.categoria = dadosCriaProduto.categoria();
    }
}
