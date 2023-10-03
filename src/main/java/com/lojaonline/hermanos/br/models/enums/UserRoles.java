package com.lojaonline.hermanos.br.models.enums;

import org.apache.catalina.User;

public enum UserRoles {

    ADMIN("admin"),
    USER("user");

    private String role;

     UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
         return role;
    }
}
