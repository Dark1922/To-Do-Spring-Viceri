package com.viceri.todo.domain.dto.input;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInput {
	
	    @NotBlank
	    private String titulo;

	    @NotBlank
	    private String descricao;
	    
	    @CreationTimestamp
	    private Date createdAt;

}
