package com.viceri.todo.domain.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.viceri.todo.domain.models.Prioridade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInput {
	
	    @NotBlank
	    private String titulo;

	    private String descricao;
	    
	    @NotNull
	    private Prioridade prioridade;

}
