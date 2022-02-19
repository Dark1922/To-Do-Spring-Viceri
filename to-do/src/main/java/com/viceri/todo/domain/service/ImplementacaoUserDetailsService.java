package com.viceri.todo.domain.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.viceri.todo.domain.models.Usuario;
import com.viceri.todo.domain.repository.UsuarioRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ImplementacaoUserDetailsService implements UserDetailsService{

	private UsuarioRepository usuarioRepository;
	
	private JdbcTemplate jdbcTemplate; //executa sql puro
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//consultar no banco o usuario
		
		Usuario usuario = usuarioRepository.findByEmail(email);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não foi encontrado"); //username não encontrado
		}
		
		return new User(usuario.getEmail(), usuario.getSenha(), usuario.getAuthorities());
		//usuario tem que passar login senha e suas autoridade
	} 

	public void insereAcessoPadrão(Long id) {
		//descobri qual é a constraint de restrição
		String constraint  = usuarioRepository.consultarConstraintRole(); //busca o nome da constrait
		
		if(constraint != null) { //se ela realmente desistir vai dar o comando de remover ela no banco
		
		//remove a constraint com nome que foi pego na consulta
		jdbcTemplate.execute("alter table usuarios_role DROP CONSTRAINT " + constraint);
		
		//insere os acesso padrão
		usuarioRepository.insereAcessoRolePadrao(id);
		}
	}

}
