package com.viceri.todo.domain.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.viceri.todo.assembler.UsuarioInpuDisassembler;
import com.viceri.todo.assembler.UsuarioModelAssembler;
import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.dto.input.UsuarioInput;
import com.viceri.todo.domain.exception.NegocioException;
import com.viceri.todo.domain.exception.UsuarioNotFoundException;
import com.viceri.todo.domain.models.Usuario;
import com.viceri.todo.domain.repository.UsuarioRepository;
import com.viceri.todo.domain.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private static final String MSG_USUARIO_NAO_ENCOTNADA = "Não existe um cadastro de Usuario com código %d";

	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;

	@Autowired
	private UsuarioInpuDisassembler usuarioInpuDisassembler;

	@Autowired
	private UsuarioRepository usuarioRepository;
 
	@Override
	@Transactional
	public UsuarioDTO save(UsuarioInput usuarioInput) {

		validarEmail(usuarioInput);
		
		Usuario usuarioAtual = usuarioInpuDisassembler.toDomainObject(usuarioInput);
		
		/* Criptografia de senha */
		String senhaCriptografada = new BCryptPasswordEncoder().encode(usuarioAtual.getPassword());
		usuarioAtual.setPassword(senhaCriptografada);
		
		return usuarioModelAssembler.toModel(usuarioRepository.save(usuarioAtual));
		}
	
	public void validarEmail(UsuarioInput usuarioInput) {
		boolean usuarioExistente = usuarioRepository.findByLogin(usuarioInput.getLogin()) != null;
		if (usuarioExistente) {
			throw new NegocioException(String.format("Já existe um usuário com este e-mail"));
		}
	}

	public Usuario buscarOuFalhar(Long id) {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNotFoundException(String.format(MSG_USUARIO_NAO_ENCOTNADA, id)));
	}

	/* contexto do usuário autenticado jwt pegando o id */
	@Override
	@Transactional
	public Long getUserId() {
		String id = SecurityContextHolder.getContext().getAuthentication().getName();
		return usuarioRepository.findByLogin(id).getId();
	}

}
