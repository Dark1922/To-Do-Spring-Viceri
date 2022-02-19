package com.viceri.todo.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viceri.todo.assembler.UsuarioInpuDisassembler;
import com.viceri.todo.assembler.UsuarioModelAssembler;
import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.dto.input.UsuarioInput;
import com.viceri.todo.domain.exception.UsuarioNotFoundException;
import com.viceri.todo.domain.models.Usuario;
import com.viceri.todo.domain.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final String MSG_USUARIO_NAO_ENCOTNADA = "Não existe um cadastro de Usuario com código %d";

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInpuDisassembler usuarioInpuDisassembler;

	@Autowired
	private UsuarioRepository usuarioRepository;

//	@Override
//	@Transactional
//	public TaskDTO update(Long id, TaskInput taskInput) {
//		Task taskAtual = BuscarOuFalhar(id);
//		taskInpuDisassembler.copyToDomainObject(taskInput, taskAtual);
//		return taskModelAssembler.toModel(taskRepository.save(taskAtual));
//	}

//	@Override
//	@Transactional
//	public void delete(Long id) {
//		try {
//			taskRepository.deleteById(id);
//		} catch (EmptyResultDataAccessException e) {
//			throw new TaskNotFoundException(String.format(MSG_TASK_NAO_ENCOTNADA, id));
//		}
//	}

//	@Override
//	@Transactional
//	public TaskDTO findById(Long id) {
//		return taskModelAssembler.toModel(BuscarOuFalhar(id));
//	}

	@Override
	@Transactional
	public UsuarioDTO save(UsuarioInput usuarioInput) {
		Usuario usuarioAtual = usuarioInpuDisassembler.toDomainObject(usuarioInput);
		return usuarioModelAssembler.toModel(usuarioRepository.save(usuarioAtual));
	}

	public Usuario BuscarOuFalhar(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException(String.format(MSG_USUARIO_NAO_ENCOTNADA, id)));
	}

	

}
