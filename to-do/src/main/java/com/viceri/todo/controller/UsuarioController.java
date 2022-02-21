package com.viceri.todo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.viceri.todo.core.security.JWTTokenAutenticacaoService;
import com.viceri.todo.domain.dto.JWTAuthResponseDTO;
import com.viceri.todo.domain.dto.LoginDTO;
import com.viceri.todo.domain.dto.UsuarioDTO;
import com.viceri.todo.domain.dto.input.UsuarioInput;
import com.viceri.todo.domain.service.UsuarioService;
import com.viceri.todo.openapi.controller.UsuarioControllerOpenApi;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController implements UsuarioControllerOpenApi {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<UsuarioDTO> salvarUsuario(@RequestBody @Valid UsuarioInput usuarioInput) {
		return ResponseEntity.ok().body(usuarioService.save(usuarioInput));
	}
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTTokenAutenticacaoService jw;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginDTO loginDTO ) {
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getLogin(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = this.jw.generateToken(authentication);
		return ResponseEntity.ok(new JWTAuthResponseDTO(token));
	}
}
