package com.viceri.todo.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.viceri.todo.dto.input.TaskInput;
import com.viceri.todo.models.Task;

@Component
public class TaskModelDisassembler {

	@Autowired
	private ModelMapper modelMapper;
	
	/*Criação de tasks novas com dados de entrada e modelMapper para converter o taskInput para Task*/
	public Task toDomainObject(TaskInput taskInput) {
		return modelMapper.map(taskInput, Task.class);
	}
	
	public void copyToDomainObject(TaskInput taskInput, Task task) {
		modelMapper.map(taskInput, task);
	}
}
