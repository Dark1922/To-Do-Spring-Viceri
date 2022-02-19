package com.viceri.todo.domain.service;

import com.viceri.todo.domain.dto.TaskDTO;
import com.viceri.todo.domain.dto.input.TaskInput;

public interface TaskService {

	TaskDTO update(Long id, TaskInput taskInput);
	
	void delete(Long id);
	
	TaskDTO save(TaskInput taskInput);
	
	public void taskCompleta(Long id);
	
	TaskDTO findById(Long id);

}
