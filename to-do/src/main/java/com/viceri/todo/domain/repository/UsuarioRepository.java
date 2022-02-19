package com.viceri.todo.domain.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viceri.todo.domain.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	@Transactional
	@Query("select u from Usuario u where u.login = ?1")
	Usuario findByLogin(String login);
	
	@Transactional
	@Query("select u from Usuario u where u.token = ?1")
	Usuario findByToken(String token);
	
	@Transactional
	@Query(value = "Select constraint_name from information_schema.constraint_column_usage"
			+ " where table_name = 'usuarios_role' and column_name = 'role_id' and constraint_name"
			+ "<> 'unique_role_user';" , nativeQuery = true) 
     String consultarConstraintRole();
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true , value = "insert into usuarios_role (usuario_id, role_id)"
			+ "	values(?1, (select id from role where nome_role = 'ROLE_USER'))")
	void insereAcessoRolePadrao(Long id);
	
	@Transactional
	@Modifying 
	@Query(nativeQuery = true ,value = "update usuario set token = ?1 where login = ?2 ")
	void atualizarTokenUser(String token, String email );
}
