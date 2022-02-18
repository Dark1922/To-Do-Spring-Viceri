package com.viceri.todo.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.viceri.todo.dto.TaskDTO;
import com.viceri.todo.models.Task;


public class TaskModelAssembler {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public TaskDTO toModel(Task task) {
		return modelMapper.map(task, TaskDTO.class);
	}
	
	public List<TaskDTO> toCollectionModel(List<Task> tasks) {
		return tasks.stream()
				.map(task -> toModel(task))
				.collect(Collectors.toList());
	}

}
