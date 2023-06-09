package com.lojaonline.hermanos.br.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lojaonline.hermanos.br.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "tb_pedidos")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PedidoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name="pedido_tem_produto", joinColumns=
    {@JoinColumn(name="pedido_id")}, inverseJoinColumns=
    {@JoinColumn(name="produto_id")})
    private List<ProdutoModel> produtos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private UsuarioModel usuario;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = true, unique = false)
    private Status statusPedido;

}
