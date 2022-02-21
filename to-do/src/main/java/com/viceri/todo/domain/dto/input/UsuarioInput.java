package com.viceri.todo.domain.dto.input;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInput {
	
	@ApiModelProperty(example = "Luis", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(example = "example@example.com", required = true)
	@NotBlank
	@Email 
	private String login;

	@ApiModelProperty(example = "9as@Mdas2", required = true)
	@NotBlank
	private String password;



}
