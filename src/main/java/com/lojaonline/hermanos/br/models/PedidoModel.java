package com.lojaonline.hermanos.br.models;

import com.lojaonline.hermanos.br.models.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "tb_pedido")
@Getter @Setter
public class PedidoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 40)
    private String usuario;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false, unique = false)
    private Status statusPedido;

    public PedidoModel(String usuario, Status statusPedido) {
        this.usuario = usuario;
        this.statusPedido = statusPedido;
    }

    public PedidoModel() {

    }
}
