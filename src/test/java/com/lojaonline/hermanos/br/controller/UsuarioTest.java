package com.lojaonline.hermanos.br.controller;

import com.lojaonline.hermanos.br.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootTest
@EnableWebMvc
@RequiredArgsConstructor
public class UsuarioTest {

    @MockBean
    private Mockito mockito;

    private final UsuarioRepository usuarioRepository;

    void findAllTest() {
       // UsuarioModel usuarioModel = new UsuarioModel();
    }

}
