package com.viceri.todo.openapi.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;

import com.viceri.todo.domain.dto.JWTAuthResponseDTO;
import com.viceri.todo.domain.dto.LoginDTO;
import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.dto.input.UsuarioInput;
import com.viceri.todo.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

@Api(tags = "Usuarios")
public interface UsuarioControllerOpenApi {

	@ApiOperation("Cadastra um Usuario")
	@ApiResponses({ @ApiResponse(code = 201, message = "Usuario cadastrado"),
			@ApiResponse(code = 400, message = "Dados do usuario inválido", response = Problem.class),
			@ApiResponse(code = 406, message = "Recurso não possui representação que pode ser aceita pelo consumidor", response = Problem.class),
			@ApiResponse(code = 500, message = "Erro interno do Servidor")})
	public ResponseEntity<UsuarioDTO> salvarUsuario(
			@ApiParam(name = "corpo", value = "Representação de um novo Usário") UsuarioInput usuarioInput);

	@ApiOperation("Autenticação de usuário")
	@ApiResponses({ @ApiResponse(code = 200, responseHeaders = {
			@ResponseHeader(name = "Usuario Autenticado", response = URI.class) }, message = "{\"Authorization\": \"Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqdmRlbWF0b....\"}", response = JWTAuthResponseDTO.class),
			@ApiResponse(code = 403, message = "Usuário não autorizado, dados inválido do usuário"),
			@ApiResponse(code = 406, message = "Recurso não possui representação que pode ser aceita pelo consumidor", response = Problem.class),
			@ApiResponse(code = 500, message = "Erro interno do Servidor"),
	@ApiResponse(code = 403, message = "Usuário não autorizado, verifique os dados e tente novamente")})
	ResponseEntity<?> authenticateUser(@ApiParam(name = "corpo", value = "Representação de Login") LoginDTO loginDTO);

}
