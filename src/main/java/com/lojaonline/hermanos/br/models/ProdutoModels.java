package com.lojaonline.hermanos.br.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "tb_produtos")
@Getter @Setter
public class ProdutoModels implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @Column(nullable = false, unique = false)
    private String descricao;

    @Column(nullable = false, unique = false)
    private Double preco;

    @Column(nullable = false, unique = false)
    private Integer qtdDisponivel;

    @Column(nullable = false, unique = false)
    private String categoria;
}
