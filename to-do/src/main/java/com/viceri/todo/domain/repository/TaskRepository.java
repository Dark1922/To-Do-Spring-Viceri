package com.viceri.todo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viceri.todo.domain.models.Prioridade;
import com.viceri.todo.domain.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	@Query(value = "select t from Task t where t.statusCompletado = false")
	List<Task> tarefasPendentes();

	@Query("select T from Task as T where T.statusCompletado = 'false' and T.prioridade = :prioridade ")
	List<Task> findTarefasPendentesFiltro(@Param("prioridade") Prioridade prioridade);

}
