package com.viceri.todo.domain.models;

import lombok.Getter;

@Getter
public enum Prioridade {
	
	MEDIA("Media"),
	BAIXA("Baixa"),
	ALTA("Alta");
	
	private String prioridade;
	
	Prioridade(String string) {
	this.prioridade = string;
	}

}
