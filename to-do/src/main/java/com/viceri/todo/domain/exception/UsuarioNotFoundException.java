package com.viceri.todo.domain.exception;

public class UsuarioNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;
	
	public UsuarioNotFoundException(String mensagem) {
		super(mensagem);
	}

}
