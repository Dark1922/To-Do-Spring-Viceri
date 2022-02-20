package com.viceri.todo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.viceri.todo.domain.models.Prioridade;
import com.viceri.todo.domain.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query(value = "select t from Task t where t.statusCompletado = false and t.usuario.id =:usuarioId")
	List<Task> tarefasPendentes(@PathVariable Long usuarioId);

	@Query("select T from Task as T where T.statusCompletado = 'false' and T.prioridade = :prioridade and "
			+ "T.usuario.id = :usuarioId ")
	List<Task> findTarefasPendentesFiltro(@Param("prioridade") Prioridade prioridade,@PathVariable Long usuarioId);
	
	@Query("select T from Task as T where T.statusCompletado = 'false' and T.prioridade = :prioridade ")
	List<Task> findTarefasPendentesFiltroAll(@Param("prioridade") Prioridade prioridade);
	
	@Query(value = "select t from Task t where t.statusCompletado = false")
	List<Task> tarefasPendentesAll();

}
