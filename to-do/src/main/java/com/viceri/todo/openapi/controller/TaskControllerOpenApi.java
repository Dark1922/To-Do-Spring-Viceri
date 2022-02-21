package com.viceri.todo.openapi.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.viceri.todo.domain.dto.TaskDTO;
import com.viceri.todo.domain.dto.input.TaskInput;
import com.viceri.todo.domain.models.Prioridade;
import com.viceri.todo.exceptionhandler.Problem;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Tasks")
public interface TaskControllerOpenApi {

	@ApiOperation("Buscar Task por id")
	@ApiResponses({ @ApiResponse(code = 400, message = "ID da task inválido", response = Problem.class),
			@ApiResponse(code = 403, message = "Usuário não autorizado, faça sua autenticação")})
	ResponseEntity<TaskDTO> findById(@ApiParam(value = "ID de uma Task", example = "1", required = true) Long id);

	@ApiOperation("Cadastra uma Task")
	@ApiResponses({ @ApiResponse(code = 201, message = "Task cadastrada"),
			@ApiResponse(code = 400, message = "Dados da task inválido", response = Problem.class),
               @ApiResponse(code = 406, message = "Recurso não possui representação que pode ser aceita pelo consumidor",
               response = Problem.class),@ApiResponse(code = 500 ,message = "Erro interno do Servidor"),
       		@ApiResponse(code = 403, message = "Usuário não autorizado, faça sua autenticação")})
                    
	ResponseEntity<TaskDTO> saveTask(
			@ApiParam(name = "corpo", value = "Representação de uma nova Task") TaskInput taskInput);

	@ApiOperation("Atualiza uma Task por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Task atualizada"),
			@ApiResponse(code = 400, message = "Task não encontrada", response = Problem.class),
            @ApiResponse(code = 406, message = "Recurso não possui representação que pode ser aceita pelo consumidor",
            response = Problem.class),@ApiResponse(code = 500 ,message = "Erro interno do Servidor"),
    		@ApiResponse(code = 403, message = "Usuário não autorizado, faça sua autenticação")})
	ResponseEntity<TaskDTO> updateTask(@ApiParam(value = "ID de uma Task", example = "2", required = true) Long id,
			@ApiParam(name = "corpo", value = "Representação de uma Task com os novos dados") TaskInput taskInput);

	@ApiOperation("Deleta uma task por id")
	@ApiResponses({ @ApiResponse(code = 204, message = "Task excluída"),
			@ApiResponse(code = 400, message = "ID da task não encontrada", response = Problem.class),
			@ApiResponse(code = 403, message = "Usuário não autorizado, faça sua autenticação")})
	ResponseEntity<Void> excluirTask(@ApiParam(value = "ID de uma Task", example = "3", required = true) Long id);

	@ApiOperation("Completa uma task por id")
	@ApiResponses({@ApiResponse(code = 400, message = "Task não encontrada", response = Problem.class) ,
		@ApiResponse(code = 204, message = "Task completada"),
		@ApiResponse(code = 403, message = "Usuário não autorizado, faça sua autenticação")})
	ResponseEntity<Void> completedTask(@ApiParam(value = "ID de uma Task", example = "4", required = true) Long id);

	@ApiOperation("Lista todas task, filtragem opcional de prioridade pelo id do usuário")
	
	@ApiResponses({@ApiResponse(code = 400, message = "ID do usuário não encontrado", response = Problem.class) ,
		@ApiResponse(code = 200, message = "OK - Lista filtrada de usuário por ID"),
		@ApiResponse(code = 403, message = "Usuário não autorizado, faça sua autenticação")})
	ResponseEntity<List<TaskDTO>> tarefaPendenteFiltroAll(
			@ApiParam(value = "prioridade", example = "ALTA") Prioridade prioridade,
			@ApiParam(value = "ID do usuário", example = "5", required = true) Long usuarioId);

	@ApiOperation("Lista todas task, filtragem opcional de prioridade geral")
	@ApiResponse(code = 403, message = "Usuário não autorizado, faça sua autenticação")
	ResponseEntity<List<TaskDTO>> tarefaPendenteFiltro(Prioridade prioridade, HttpHeaders headers);

}
