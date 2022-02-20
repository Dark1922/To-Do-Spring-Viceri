package com.viceri.todo.domain.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

 private static final String MSG_TASK_NAO_ENCOTNADA = "Não existe um cadastro de task com código %d";
 private static final String MSG_NAO_PODE_MODIFICAR_TAKS_DE_OUTROS_USUARIO = "Não pode modificar task de outro usuário";
 private static final String MSG_USUARIO_NAO_CORRESPONDENTE = "Não pode acessar tasks com usuário inválido";
	

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
		contextValidateJwt(id);
		taskInpuDisassembler.copyToDomainObject(taskInput, taskAtual);
		return taskModelAssembler.toModel(taskRepository.save(taskAtual));
	}

	@Override
	@Transactional
	public void delete(Long id) {
		try {
			contextValidateJwt(id);
			taskRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new TaskNotFoundException(String.format(MSG_TASK_NAO_ENCOTNADA, id));
		}
	}

	@Override
	@Transactional  
	public void taskCompleta(Long id) {
		contextValidateJwt(id);
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
		
		Long usuarioId = usuarioService.getUserId();
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		Task taskAtual = taskInpuDisassembler.toDomainObject(taskInput);
		taskAtual.setUsuario(usuario);
		
		return taskModelAssembler.toModel(taskRepository.save(taskAtual));
	}

	@Override
	@Transactional
	public List<TaskDTO> findTarefasPendentes(Long usuarioId) {
		contextValidateConsultaJwt(usuarioId);
		return taskModelAssembler.toCollectionModel(taskRepository.tarefasPendentes(usuarioId));
				 
	}
	
	@Override
	@Transactional
	public List<TaskDTO> findTarefasPendentesFiltro(Prioridade prioridade, Long usuarioId) {
		contextValidateConsultaJwt(usuarioId);
		return taskModelAssembler.toCollectionModel(taskRepository.findTarefasPendentesFiltro(prioridade, usuarioId));
	}
	
	/*Validações com contexto do usuário autenticado*/
	public void contextValidateJwt(Long id) {
		Task taskAtual = BuscarOuFalhar(id);
		Long usuarioId = usuarioService.getUserId();
		
		if(usuarioId != taskAtual.getUsuario().getId()) {
			throw new NegocioException(String.format(MSG_NAO_PODE_MODIFICAR_TAKS_DE_OUTROS_USUARIO));
		}
	}
	
	/*Usuário só pode acessar suas próprias tarefas pendente e não de outros usuários*/
	public void contextValidateConsultaJwt(Long id) {
		Usuario usuarioAtual = usuarioService.buscarOuFalhar(id);
		Long usuarioId = usuarioService.getUserId();
		
		if(usuarioId != usuarioAtual.getId()) {
			throw new NegocioException(String.format(MSG_USUARIO_NAO_CORRESPONDENTE));
		}
	}
	
	public Task BuscarOuFalhar(Long id) {
		return taskRepository.findById(id)
				.orElseThrow(() -> new TaskNotFoundException(String.format(MSG_TASK_NAO_ENCOTNADA, id)));
	}
	
}
