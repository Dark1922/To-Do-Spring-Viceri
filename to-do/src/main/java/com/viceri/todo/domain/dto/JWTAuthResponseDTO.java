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
public class JWTAuthResponseDTO {

	@ApiModelProperty(example = " \"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdmRlbWF0b3MwMDRAZ21haWwuY29tIiwi.....\"")
    private String tokenAccess;
    
	@ApiModelProperty(example = "Bearer")
    private String tokenType = "Bearer";

    public JWTAuthResponseDTO(String token){
        setTokenAccess(token);
    }

}