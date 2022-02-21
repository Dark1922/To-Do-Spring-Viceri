package com.viceri.todo.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;

@JsonInclude(value = Include.NON_NULL)
@Getter
@Builder
public class Problem {

	@ApiModelProperty(example = "400", position = 1)
	private Integer status;
	
	@ApiModelProperty(example = "2021-12-01T18:09:02.70844Z", position = 5)
	private OffsetDateTime timestamp;
	
	@ApiModelProperty(example = "https://todo-viceri.com/dados-invalidos", position = 10)
	private String type;
	
	@ApiModelProperty(example = "Dados inválidos", position = 15)
	private String title;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", 
			position = 20)
	private String detail;
	
	@ApiModelProperty(example = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.", 
			position = 25)
	private String userMessage;
	
	@ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)", 
			position = 30)
	private List<Object> objects;

	@ApiModel("Problema")
	@Getter
	@Builder
	public static class Object {

		@ApiModelProperty(example = "Recurso não encontrado, requisição inváçida", position = 5)
		private String name;

		@ApiModelProperty(example = "Aceitamos somente application/json, acessou uma requisição inválida", position = 10)
		private String userMessage;

	}
}