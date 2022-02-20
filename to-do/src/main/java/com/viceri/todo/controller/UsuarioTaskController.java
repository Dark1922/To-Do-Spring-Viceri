package com.viceri.todo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.viceri.todo.domain.dto.TaskDTO;
import com.viceri.todo.domain.dto.input.TaskInput;
import com.viceri.todo.domain.models.Prioridade;
import com.viceri.todo.domain.service.TaskService;
import com.viceri.todo.openapi.controller.TaskControllerOpenApi;

@RestController
@RequestMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioTaskController implements TaskControllerOpenApi {

	@Autowired
	private TaskService taskService;

	@GetMapping("/{id}")
	public ResponseEntity<TaskDTO> findById(@PathVariable Long id) {
		return ResponseEntity.ok(taskService.findById(id));
	}

	@PostMapping
	public ResponseEntity<TaskDTO> saveTask(@RequestBody @Valid TaskInput taskInput) {
		return ResponseEntity.status(HttpStatus.CREATED).body(taskService.save(taskInput));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable Long id, @RequestBody @Valid TaskInput taskInput) {
		return ResponseEntity.ok(taskService.update(id, taskInput));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> excluirTask(@PathVariable Long id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/completar/{id}")
	public ResponseEntity<Void> completedTask(@PathVariable Long id) {
		taskService.taskCompleta(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/pendente/{usuarioId}")
	public ResponseEntity<List<TaskDTO>> tarefaPendenteFiltro(@RequestParam(required = false) Prioridade prioridade,
			@PathVariable Long usuarioId) {
		
		if(prioridade != null) {
		return ResponseEntity.ok().body(taskService.findTarefasPendentesFiltro(prioridade,usuarioId));
		}
		
		return ResponseEntity.ok().body(taskService.findTarefasPendentes(usuarioId));
	}
	
	

}
