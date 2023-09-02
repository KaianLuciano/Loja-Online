package com.lojaonline.hermanos.br.models;

import com.lojaonline.hermanos.br.models.dto.usuario.DadosAtualizaUsuario;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tb_usuarios")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @Column(name = "cpf", unique = true, nullable = true, length = 11)
    private String cpf;
    private String nome;
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Hidden
    private List<Pedido> pedidos;

    @OneToOne
    @Hidden
    private Carrinho carrinho;

    public Usuario(DadosAtualizaUsuario usuarioAtualizar, Usuario usuarioOriginal) {
        this.cpf = usuarioOriginal.getCpf();
        this.nome = usuarioOriginal.getNome() == null ? usuarioOriginal.getNome() : usuarioAtualizar.nome();
        this.email = usuarioOriginal.getEmail() == null ? usuarioOriginal.getEmail() : usuarioAtualizar.email();
        this.senha = usuarioOriginal.getSenha() == null ? usuarioOriginal.getSenha() : usuarioAtualizar.senha();
        this.pedidos = usuarioOriginal.getPedidos();
        this.carrinho = usuarioOriginal.getCarrinho();
    }
}
