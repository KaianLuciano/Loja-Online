package com.lojaonline.hermanos.br;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
	Criador do Projeto: Kaian Luciano Alves Dos Santos
	Projeto: Loja Online
 */

@OpenAPIDefinition(info = @Info(title = "Loja Online", version = "1", description = "Essa é uma api que tem como objetivo, o gerenciamento dos dados de uma loja online"))
@SpringBootApplication
public class LojaOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(LojaOnlineApplication.class, args );
	}

	// http://localhost:8080/swagger-ui/index.html#/
	// https://loja-online-production.up.railway.app/swagger-ui/index.html#/
}
