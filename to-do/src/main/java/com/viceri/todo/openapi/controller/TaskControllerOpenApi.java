package com.viceri.todo.openapi.controller;

import java.util.List;

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
			@ApiResponse(code = 404, message = "Task não encontrada", response = Problem.class) })
	ResponseEntity<TaskDTO> findById(@ApiParam(value = "ID de uma Task", example = "1", required = true) Long id);

	@ApiOperation("Cadastra uma Task")
	@ApiResponses({ @ApiResponse(code = 201, message = "Task cadastrada"),
			@ApiResponse(code = 400, message = "Dados da task inválido", response = Problem.class) })
	ResponseEntity<TaskDTO> saveTask(
			@ApiParam(name = "corpo", value = "Representação de uma nova Task") TaskInput taskInput);

	@ApiOperation("Atualiza uma Task por id")
	@ApiResponses({ @ApiResponse(code = 200, message = "Task atualizada"),
			@ApiResponse(code = 404, message = "Task	 não encontrada", response = Problem.class) })
	ResponseEntity<TaskDTO> updateTask(@ApiParam(value = "ID de uma Task", example = "2", required = true) Long id,
			@ApiParam(name = "corpo", value = "Representação de uma Task com os novos dados") TaskInput taskInput);

	@ApiOperation("Deleta uma task por id")
	@ApiResponses({ @ApiResponse(code = 204, message = "Task excluída"),
			@ApiResponse(code = 404, message = "Task não encontrada", response = Problem.class) })
	ResponseEntity<Void> excluirTask(@ApiParam(value = "ID de uma Task", example = "3", required = true) Long id);

	@ApiOperation("Completa uma task por id")
	@ApiResponses({ @ApiResponse(code = 204, message = "Task completada"),
			@ApiResponse(code = 404, message = "Task não encontrada", response = Problem.class) })
	ResponseEntity<Void> completedTask(@ApiParam(value = "ID de uma Task", example = "4", required = true) Long id);

	@ApiOperation("Lista todas task, filtragem opcional de prioridade")
	ResponseEntity<List<TaskDTO>> tarefaPendenteFiltro(
			@ApiParam(value = "prioridade", example = "ALTA") Prioridade prioridade,
			@ApiParam(value = "ID de uma Task", example = "5", required = true) Long usuarioId);

}
