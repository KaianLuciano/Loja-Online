package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.models.Carrinho;
import com.lojaonline.hermanos.br.models.Usuario;
import com.lojaonline.hermanos.br.models.dto.usuario.DadosAtualizaUsuario;
import com.lojaonline.hermanos.br.models.dto.usuario.DadosListagemUsuario;
import com.lojaonline.hermanos.br.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CarrinhoService carrinhoService;


    public List<DadosListagemUsuario> findAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> new DadosListagemUsuario(usuario)).toList();
    }

    public DadosListagemUsuario findById(String cpf){
        return new DadosListagemUsuario(usuarioRepository.findById(cpf).get());
    }

    @Transactional
    public DadosListagemUsuario saveUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);

        if(usuario.getCarrinho() == null){
            Carrinho carrinho = new Carrinho();
            carrinho.setUsuario(usuario);
            carrinhoService.saveCarrinho(carrinho);
            usuario.setCarrinho(carrinho);
            usuarioRepository.save(usuario);
        }

        return new DadosListagemUsuario(usuario);
    }

    public DadosListagemUsuario updateUsuario(String cpfUsuario, DadosAtualizaUsuario usuarioAtualizar) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(cpfUsuario);
        if(usuarioEncontrado.isEmpty()) {
            throw new NoSuchElementException();
        }

        Usuario usuarioAtualizado = new Usuario(usuarioAtualizar, usuarioEncontrado.get());
        Usuario usuarioSalvo = usuarioRepository.save(usuarioAtualizado);

        return new DadosListagemUsuario(usuarioSalvo);
    }

}
