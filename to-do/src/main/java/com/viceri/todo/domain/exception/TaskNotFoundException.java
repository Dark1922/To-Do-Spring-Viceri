package com.viceri.todo.domain.exception;

public class TaskNotFoundException extends EntityNotFoundException {
	
	private static final long serialVersionUID = 1L;
	
	public TaskNotFoundException(String mensagem) {
		super(mensagem);
	}

}
