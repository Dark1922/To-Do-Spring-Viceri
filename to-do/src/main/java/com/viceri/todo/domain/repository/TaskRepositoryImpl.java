package com.viceri.todo.domain.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

import com.viceri.todo.domain.models.Task;

@Repository
public class TaskRepositoryImpl {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	@Lazy // só instancia ela no momento que for preciso preguiçoso
	private TaskRepository taskRepository;

	public List<Task> find(Task task) {

		var builder = manager.getCriteriaBuilder(); // fábrica de de elementos pra consulta

		var criteria = builder.createQuery(Task.class); // construtor de clausulas

		var root = criteria.from(Task.class); // From restaurante

		var predicates = new ArrayList<Predicate>();

		if (task.getPrioridade() != null) {
			predicates.add(builder.equal(root.get("prioridade"), task.getPrioridade()));
		}
		if (task.getDescricao() != null) {
			predicates.add(builder.equal(root.get("descricao"), task.getDescricao()));
		}
		if (task.getTitulo() != null) {
			predicates.add(builder.equal(root.get("titulo"), task.getTitulo()));
		}

		criteria.where(predicates.toArray(new Predicate[0])); // instancia de array preenchido com todos predicates
																// passados

		var query = manager.createQuery(criteria);
		return query.getResultList();

	}

}
