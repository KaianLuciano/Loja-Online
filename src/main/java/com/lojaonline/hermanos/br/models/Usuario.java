package com.lojaonline.hermanos.br.models;

import com.lojaonline.hermanos.br.models.dto.usuario.DadosAtualizaUsuario;
import com.lojaonline.hermanos.br.models.enums.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_usuarios")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Usuario {

    @Id
    @Column(name = "cpf", unique = true, nullable = true, length = 11)
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private UserRoles role;

    @OneToMany(mappedBy = "usuario", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @OneToOne
    private Carrinho carrinho;

    public Usuario(DadosAtualizaUsuario usuarioAtualizar, Usuario usuarioOriginal) {
        this.cpf = usuarioOriginal.getCpf();
        this.nome = usuarioOriginal.getNome() == null ? usuarioOriginal.getNome() : usuarioAtualizar.nome();
        this.email = usuarioOriginal.getEmail() == null ? usuarioOriginal.getEmail() : usuarioAtualizar.email();
        this.senha = usuarioOriginal.getSenha() == null ? usuarioOriginal.getSenha() : usuarioAtualizar.senha();
        this.pedidos = usuarioOriginal.getPedidos();
        this.carrinho = usuarioOriginal.getCarrinho();
    }

    public Usuario(String login, String encryptedPassword, UserRoles role) {
        this.email = login;
        this.senha = encryptedPassword;
        this.role = role;
    }

}
