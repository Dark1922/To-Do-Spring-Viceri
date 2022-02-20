package com.viceri.todo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.dto.input.UsuarioInput;
import com.viceri.todo.domain.service.UsuarioService;

@RestController
@RequestMapping(path = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody @Valid UsuarioInput usuarioInput) {
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.save(usuarioInput));
	}


}
