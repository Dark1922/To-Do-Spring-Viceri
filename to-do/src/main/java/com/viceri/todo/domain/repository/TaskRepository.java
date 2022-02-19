package com.viceri.todo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.viceri.todo.domain.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	@Query(value = "from Task t where t.statusCompletado = false")
	List<Task> tarefasPendentes();

}
