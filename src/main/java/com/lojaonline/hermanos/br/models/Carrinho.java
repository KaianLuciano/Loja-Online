package com.lojaonline.hermanos.br.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_carrinhos")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Carrinho implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valorTotal = 0.0;

    @ManyToMany
    @JoinTable(name="carrinho_tem_produtos", joinColumns=
    {@JoinColumn(name="carrinho_id")}, inverseJoinColumns=
    {@JoinColumn(name="produto_id")})
    private List<Produto> produtos = new ArrayList<>();

    @OneToOne
    @JsonIgnore
    private Usuario usuario;
}
