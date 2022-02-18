package com.viceri.todo.dto.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInputDTO {
	
	    @NotBlank
	    private String titulo;

	    @NotBlank
	    private String drescricao;

}
