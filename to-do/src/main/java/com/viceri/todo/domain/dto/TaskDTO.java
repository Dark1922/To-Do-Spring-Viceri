package com.viceri.todo.domain.dto;

import com.viceri.todo.domain.models.Prioridade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {

	private Long id;

	private String titulo;

	private String descricao;

	private Boolean statusCompletado;

	private Prioridade prioridade;

}
