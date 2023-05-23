package com.lojaonline.hermanos.br.DTO;

import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.PedidoModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String cpf;
    private String nome;
    private String email;
    private List<PedidoModel> pedidos;
    private CarrinhoModel carrinho;

}
