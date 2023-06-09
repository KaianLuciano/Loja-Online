package com.lojaonline.hermanos.br.service;

import com.lojaonline.hermanos.br.DTO.UsuarioDTO;
import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import com.lojaonline.hermanos.br.repository.PedidoRepository;
import com.lojaonline.hermanos.br.repository.ProdutoRepository;
import com.lojaonline.hermanos.br.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    final UsuarioRepository usuarioRepository;
    final PedidoRepository pedidoRepository;
    final ProdutoRepository produtoRepository;
    final CarrinhoService carrinhoService;


    public List<UsuarioDTO> findAll(){
        List<UsuarioModel> usuarioModel = usuarioRepository.findAll();
        return usuarioModel.stream().map(x -> new UsuarioDTO(x)).toList();
    }

    public UsuarioDTO findById(String cpf){
        return new UsuarioDTO(usuarioRepository.findById(cpf).get());
    }

    public Optional<UsuarioModel> findByIdPrivate(String cpf){
        Optional<UsuarioModel> usuario = usuarioRepository.findById(cpf);
        return usuario;
    }

    @Transactional
    public UsuarioDTO saveUsuario(UsuarioModel usuario) {
        usuarioRepository.save(usuario);

        if(usuario.getCarrinhoModel() == null){
            CarrinhoModel carrinhoModel = new CarrinhoModel();
            carrinhoModel.setUsuario(usuario);
            carrinhoService.saveCarrinho(carrinhoModel);
            usuario.setCarrinhoModel(carrinhoModel);
            usuarioRepository.save(usuario);
        }

        return new UsuarioDTO(usuario);
    }

}
