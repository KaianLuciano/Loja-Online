package com.lojaonline.hermanos.br.models;

import com.lojaonline.hermanos.br.models.dto.usuario.DadosAtualizaUsuario;
import com.lojaonline.hermanos.br.models.enums.UserRoles;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "tb_usuarios")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @Column(name = "cpf", unique = true, nullable = true, length = 11)
    private String cpf;
    private String nome;
    private String email;
    private String senha;
    private UserRoles role;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UserRoles.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
