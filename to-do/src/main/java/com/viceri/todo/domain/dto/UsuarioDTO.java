package com.viceri.todo.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
	
	@ApiModelProperty(example = "1")
	private Long id;

	@ApiModelProperty(example = "joão")
	private String nome;

	@ApiModelProperty(example = "example@email.com")
	private String email;


}
