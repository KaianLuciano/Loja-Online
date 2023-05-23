package com.lojaonline.hermanos.br.DTO;

import com.lojaonline.hermanos.br.models.CarrinhoModel;
import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String email;
    private List<PedidoModel> pedidos;
    private CarrinhoModel carrinho;

    public UsuarioDTO(UsuarioModel entity) {
        /*
         * Classe utilitaria que contem um metodo que auxilia a preencher todos os atributos, usado quando se tem
         * muitos atributos em uma classe. Se os nomes dos atributos das duas classes forem iguais, ele copia tudo.
         */
        BeanUtils.copyProperties(entity, this);
    }

}
