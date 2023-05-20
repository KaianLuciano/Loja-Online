package com.lojaonline.hermanos.br.controller.util;

import com.lojaonline.hermanos.br.models.PedidoModel;
import com.lojaonline.hermanos.br.models.UsuarioModel;
import org.springframework.stereotype.Component;

@Component
public class ControllerUtils {

    public boolean isProdutoEmpty(UsuarioModel usuario) {
        if (usuario.getPedidos() == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isProdutoEmpty(PedidoModel pedido) {
        if (pedido.getUsuario() == null) {
            return true;
        } else {
            return false;
        }
    }

}
