package com.viceri.todo.domain.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DisneyException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
    private String message;

    public DisneyException(HttpStatus status, String message, String newMessage){
        setStatus(status);
        setMessage(message);
        setMessage(newMessage);
    }
    
}
