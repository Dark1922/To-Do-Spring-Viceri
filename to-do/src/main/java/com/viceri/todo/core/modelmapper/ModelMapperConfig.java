package com.viceri.todo.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.models.Usuario;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
		modelMapper.createTypeMap(Usuario.class, UsuarioDTO.class).addMapping(Usuario::getLogin, UsuarioDTO::setEmail);
//		.addMapping(Usuario::getPassword, UsuarioDTO::setSenha)
		
		return modelMapper;
	}
	
}