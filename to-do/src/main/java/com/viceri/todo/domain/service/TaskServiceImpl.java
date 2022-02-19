package com.viceri.todo.domain.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.viceri.todo.assembler.TaskInpuDisassembler;
import com.viceri.todo.assembler.TaskModelAssembler;
import com.viceri.todo.domain.dto.TaskDTO;
import com.viceri.todo.domain.dto.input.TaskInput;
import com.viceri.todo.domain.exception.TaskNotFoundException;
import com.viceri.todo.domain.exception.TokenNotFoundException;
import com.viceri.todo.domain.models.Task;
import com.viceri.todo.domain.models.Usuario;
import com.viceri.todo.domain.repository.TaskRepository;
import com.viceri.todo.domain.repository.UsuarioRepository;

@Service
public class TaskServiceImpl implements TaskService {

	private static final String MSG_TASK_NAO_ENCOTNADA = "Não existe um cadastro de Task com código %d";
	
	private static final String MSG_TOKEN_NAO_ENCOTNADA = "Não existe um cadastro de Task com código %d";

	@Autowired
	private TaskModelAssembler taskModelAssembler;

	@Autowired
	private TaskInpuDisassembler taskInpuDisassembler;

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public TaskDTO update(Long id, TaskInput taskInput) {
		Task taskAtual = BuscarOuFalhar(id);
		taskInpuDisassembler.copyToDomainObject(taskInput, taskAtual);
		return taskModelAssembler.toModel(taskRepository.save(taskAtual));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			taskRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new TaskNotFoundException(String.format(MSG_TASK_NAO_ENCOTNADA, id));
		}
	}

	@Override
	@Transactional  
	public void taskCompleta(Long id) {
		Task taskAtual = BuscarOuFalhar(id);
		taskAtual.setStatusCompletado(true);
	}
	
	@Override
	@Transactional
	public TaskDTO findById(Long id) {
		return taskModelAssembler.toModel(BuscarOuFalhar(id));
	}
	
	@Override
	@Transactional
	public TaskDTO save(TaskInput taskInput) {
		Task taskAtual = taskInpuDisassembler.toDomainObject(taskInput);
		return taskModelAssembler.toModel(taskRepository.save(taskAtual));
	}

	public Task BuscarOuFalhar(Long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException(String.format(MSG_TASK_NAO_ENCOTNADA, id)));
	}
	
	@Override
	public List<TaskDTO> findTarefaNaoCompletada() {
		return taskModelAssembler.toCollectionModel(taskRepository.tarefasPendentes());
	}
	
}
