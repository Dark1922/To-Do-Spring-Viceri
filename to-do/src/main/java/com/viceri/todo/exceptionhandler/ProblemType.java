package com.viceri.todo.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
	DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"), ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
	PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
	MENSAGEM_INCOMPREENSIVEL("/mensagem-imcompreensivel", "Mensagem incompreensível"),
	RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
	ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

	private String title;
	private String uri;

	ProblemType(String path, String title) {
		this.uri = "https://todo-viceri.com.br" + path;
		this.title = title;
	}
}