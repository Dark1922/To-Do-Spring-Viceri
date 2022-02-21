package com.viceri.todo.domain.dto;

import com.viceri.todo.domain.models.Prioridade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

	@ApiModelProperty(example = "2")
	private Long id;

	@ApiModelProperty(example = "Fazer Crud completo em 1 dia com Angular usando Spring Boot Rest", position = 1)
	private String titulo;

	@ApiModelProperty(example = "Ultilizar métodos http GET, PUT , DELETE, POST", position = 5)
	private String descricao;

	@ApiModelProperty(example = "value = 'false' é uma tarefe incompleta", position = 10)
	private Boolean statusCompletado;

	@ApiModelProperty(example = "ALTA", position = 15)
	private Prioridade prioridade;

}
