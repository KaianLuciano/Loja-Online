package com.lojaonline.hermanos.br.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuarios")
@Getter @Setter
public class UsuarioModel {

    @Id
    @Column(name = "cpf", unique = true, nullable = true, length = 11)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<PedidoModel> pedidos;

    @OneToOne
    private CarrinhoModel carrinhoModel;
}
