package com.viceri.todo.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
public class Task {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private  Long id;

	    @Column(nullable = false)
	    private String titulo;

	    @Column(nullable = false)
	    private String drescricao;
	    
	    @Column(nullable = false)
	    private boolean prioridade;

	    @CreationTimestamp
	    @Column(name = "created_at", nullable = false, updatable = false)
	    private Date createdAt;

	   

}
