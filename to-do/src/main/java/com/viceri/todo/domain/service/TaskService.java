package com.viceri.todo.domain.service;

import java.util.List;

import com.viceri.todo.domain.dto.TaskDTO;
import com.viceri.todo.domain.dto.input.TaskInput;
import com.viceri.todo.domain.models.Prioridade;

public interface TaskService {

	TaskDTO update(Long id, TaskInput taskInput);

	void delete(Long id);

	TaskDTO save(TaskInput taskInput);

	void taskCompleta(Long id);

	TaskDTO findById(Long id);

	List<TaskDTO> findTarefasPendentes(Long usuarioId);

	List<TaskDTO> findTarefasPendentesFiltro(Prioridade prioridade, Long usuarioId);

}
