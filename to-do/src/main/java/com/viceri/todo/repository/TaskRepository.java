package com.viceri.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viceri.todo.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
	
	

}
