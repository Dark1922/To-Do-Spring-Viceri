package com.viceri.todo.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoginDTO {

	@ApiModelProperty(example = "example@example.com", required = true)
    private String login;
    
	@ApiModelProperty(example = "2130@MVsad#", required = true)
    private String password;
    
}