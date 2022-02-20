package com.viceri.todo.domain.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import com.viceri.todo.assembler.TaskInpuDisassembler;
import com.viceri.todo.assembler.TaskModelAssembler;
import com.viceri.todo.domain.dto.TaskDTO;
import com.viceri.todo.domain.dto.input.TaskInput;
import com.viceri.todo.domain.exception.NegocioException;
import com.viceri.todo.domain.exception.TaskNotFoundException;
import com.viceri.todo.domain.models.Prioridade;
import com.viceri.todo.domain.models.Task;
import com.viceri.todo.domain.models.Usuario;
import com.viceri.todo.domain.repository.TaskRepository;
import com.viceri.todo.domain.service.TaskService;
import com.viceri.todo.domain.service.UsuarioService;

@Service
public class TaskServiceImpl implements TaskService {

 private static final String MSG_TASK_NAO_ENCOTNADA = "Não existe um cadastro de Task com código %d";
 private static final String MSG_TASK_NAO_PODE_EXCLUIR_DE_OUTRO_USUARIO = "Não pode excluir umas task de outro usuário com código %d";
 private static final String MSG_NAO_PODE_COMPLETAR_TASK_DE_OUTRO_USUARIO = "Não pode completar task de outro usuário com código %d";
 private static final String MSG_NAO_PODE_ALTERAR_TASK_DE_OUTRO_USUARIO = "Não pode alterar task de outro usuário com código %d";
	

	@Autowired
	private TaskModelAssembler taskModelAssembler;

	@Autowired
	private TaskInpuDisassembler taskInpuDisassembler;

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	@Transactional
	public TaskDTO update(Long id, TaskInput taskInput) {
		Task taskAtual = BuscarOuFalhar(id);
		Long usuarioId = usuarioService.getUserId();
		
		if(usuarioId != taskAtual.getUsuario().getId()) {
			throw new NegocioException(String.format(MSG_NAO_PODE_ALTERAR_TASK_DE_OUTRO_USUARIO, id));
		}
		taskInpuDisassembler.copyToDomainObject(taskInput, taskAtual);
		return taskModelAssembler.toModel(taskRepository.save(taskAtual));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			Task taskAtual = BuscarOuFalhar(id);
			Long usuarioId = usuarioService.getUserId();
			
			if(usuarioId != taskAtual.getUsuario().getId()) {
				throw new NegocioException(String.format(MSG_TASK_NAO_PODE_EXCLUIR_DE_OUTRO_USUARIO, id));
			}
			taskRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new TaskNotFoundException(String.format(MSG_TASK_NAO_ENCOTNADA, id));
		}
	}

	@Override
	@Transactional  
	public void taskCompleta(Long id) {
		Task taskAtual = BuscarOuFalhar(id);
		Long usuarioId = usuarioService.getUserId();
		
		if(usuarioId != taskAtual.getUsuario().getId()) {
			throw new NegocioException(String.format(MSG_NAO_PODE_COMPLETAR_TASK_DE_OUTRO_USUARIO, id));
		}
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
		
		Long usuarioId = usuarioService.getUserId();
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		Task taskAtual = taskInpuDisassembler.toDomainObject(taskInput);
		taskAtual.setUsuario(usuario);
		
		return taskModelAssembler.toModel(taskRepository.save(taskAtual));
	}

	@Override
	@Transactional
	public List<TaskDTO> findTarefasPendentes() {
		
		Task task = new Task();
		Long usuarioId = usuarioService.getUserId();
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		task.getUsuario().setId(usuario.getId());
		
		return taskModelAssembler.toCollectionModel(taskRepository.tarefasPendentes());
				
	}
	
	public List<TaskDTO> findTarefasPendentesFiltro(Prioridade prioridade) {
		
		Task task = new Task();
		Long usuarioId = usuarioService.getUserId();
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		task.getUsuario().setId(usuario.getId());
		
		return taskModelAssembler.toCollectionModel(taskRepository.findTarefasPendentesFiltro(prioridade));
	}
	
	public Task BuscarOuFalhar(Long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException(String.format(MSG_TASK_NAO_ENCOTNADA, id)));
	}
	
}
