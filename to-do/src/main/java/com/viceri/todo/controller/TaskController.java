package com.viceri.todo.controller;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.viceri.todo.domain.dto.TaskDTO;
import com.viceri.todo.domain.dto.input.TaskInput;
import com.viceri.todo.domain.service.TaskService;

@RestController
@RequestMapping(path = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

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
	public ResponseEntity<Void> excluir(@PathVariable Long id) {
		taskService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{taskId}/completar")
     public ResponseEntity<Void> completar(@PathVariable Long taskId) {
		taskService.taskCompleta(taskId);
		return ResponseEntity.noContent().build();
     }	

}
