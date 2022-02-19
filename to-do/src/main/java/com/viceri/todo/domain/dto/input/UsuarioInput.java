package com.viceri.todo.domain.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@NotBlank
	private String nome;

	@NotBlank
	@Email
	private String login;

	@NotBlank
	private String password;



}
