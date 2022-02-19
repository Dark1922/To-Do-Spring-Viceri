package com.viceri.todo.domain.service;

import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.dto.input.UsuarioInput;

public interface UsuarioService {


	
	UsuarioDTO save(UsuarioInput usuarioInput);
	
	

}
