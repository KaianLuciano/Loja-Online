package com.lojaonline.hermanos.br.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "tb_pedidos")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name="pedido_tem_produto", joinColumns=
    {@JoinColumn(name="pedido_id")}, inverseJoinColumns=
    {@JoinColumn(name="produto_id")})
    private List<Produto> produtos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @Enumerated(EnumType.ORDINAL)
    private Status statusPedido;

    public Pedido(List<Produto> produtosEncontrados, Usuario usuarioEncontrado) {
        this.usuario = usuarioEncontrado;
        this.produtos = produtosEncontrados;
        this.statusPedido = Status.PENDENTE;
    }
}
