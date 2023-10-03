package com.lojaonline.hermanos.br.models.dto.security;

import com.lojaonline.hermanos.br.models.enums.UserRoles;

public record RegisterDTO(String login, String password, UserRoles role) {
}
