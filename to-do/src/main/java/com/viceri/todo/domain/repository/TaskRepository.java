package com.viceri.todo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viceri.todo.domain.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	

}
