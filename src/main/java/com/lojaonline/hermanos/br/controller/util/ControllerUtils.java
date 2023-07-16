package com.lojaonline.hermanos.br.controller.util;

import com.lojaonline.hermanos.br.models.Pedido;
import com.lojaonline.hermanos.br.models.Usuario;
import org.springframework.stereotype.Component;

@Component
public class ControllerUtils {

    public boolean isProdutoEmpty(Usuario usuario) {
        if (usuario.getPedidos() == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isProdutoEmpty(Pedido pedido) {
        if (pedido.getUsuario() == null) {
            return true;
        } else {
            return false;
        }
    }

}
