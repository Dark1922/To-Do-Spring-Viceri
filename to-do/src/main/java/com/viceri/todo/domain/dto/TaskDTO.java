package com.viceri.todo.domain.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDTO {
	
	private Long id;
	
	    private String titulo;

	    private String descricao;
	    
	    private Boolean statusCompletado;

	    private Date createdAt;

}
