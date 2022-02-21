package com.viceri.todo.domain.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.viceri.todo.domain.models.Prioridade;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskInput {
	
	    @ApiModelProperty(example = "Fazer implementações de formas de pagamento", required = true)
	    @NotBlank
	    private String titulo;

	    @ApiModelProperty(example = "Terminar em 3 dias") 
	    private String descricao;
	    
	    @ApiModelProperty(example = "ALTA", required = true)
	    @NotNull
	    private Prioridade prioridade;

}
