package com.lojaonline.hermanos.br.security.controller;

import com.lojaonline.hermanos.br.models.Usuario;
import com.lojaonline.hermanos.br.models.dto.security.AuthenticationDTO;
import com.lojaonline.hermanos.br.models.dto.security.LoginResponseDTO;
import com.lojaonline.hermanos.br.models.dto.security.RegisterDTO;
import com.lojaonline.hermanos.br.repository.UsuarioRepository;
import com.lojaonline.hermanos.br.security.service.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamepassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = authenticationManager.authenticate(usernamepassword);

        var token = tokenService.generatedToken((Usuario) auth);

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO registerDTO) {
        if(this.usuarioRepository.findByEmail(registerDTO.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDTO.password());
        Usuario usuario = new Usuario(registerDTO.login(), encryptedPassword, registerDTO.role());

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}
