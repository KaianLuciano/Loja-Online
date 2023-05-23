package com.lojaonline.hermanos.br.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_carrinho")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CarrinhoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name="carrinho_tem_produtos", joinColumns=
    {@JoinColumn(name="pedido_id")}, inverseJoinColumns=
    {@JoinColumn(name="produto_id")})
    private List<ProdutoModel> produtos;

    @OneToOne
    @JsonIgnore
    private UsuarioModel usuario;
}
