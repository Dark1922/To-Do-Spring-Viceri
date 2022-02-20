package com.viceri.todo.domain.service;

import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.dto.input.UsuarioInput;
import com.viceri.todo.domain.models.Usuario;

public interface UsuarioService {


	
	UsuarioDTO save(UsuarioInput usuarioInput);
	
	Usuario buscarOuFalhar(Long id);
	
	Long getUserId();


	
	

}
