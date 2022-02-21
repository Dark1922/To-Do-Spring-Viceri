package com.viceri.todo.domain.exception;

public class TokenNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;
	
	public TokenNotFoundException(String mensagem) {
		super(mensagem);
	}

}
