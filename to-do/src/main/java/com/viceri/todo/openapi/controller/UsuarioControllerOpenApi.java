package com.viceri.todo.openapi.controller;

import org.springframework.http.ResponseEntity;

import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.dto.input.UsuarioInput;
import com.viceri.todo.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Cadastra um Usuario")
	@ApiResponses({ @ApiResponse(code = 201, message = "Usuario cadastrado"),
			@ApiResponse(code = 400, message = "Dados do usuario inválido", response = Problem.class) })
	public ResponseEntity<UsuarioDTO> salvarUsuario(
			@ApiParam(name = "corpo", value = "Representação de um novo Usário") UsuarioInput usuarioInput);

}
