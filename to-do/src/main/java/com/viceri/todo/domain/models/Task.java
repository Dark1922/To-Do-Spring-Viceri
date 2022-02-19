package com.viceri.todo.domain.models;

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

	    @Column(nullable = false, length = 80)
	    private String titulo;

	    @Column(nullable = false, length = 1000)
	    private String descricao;
	    
	    private boolean taskCompleta;

	    @CreationTimestamp
	    private Date createdAt;
	    
	    public void prioridade() {
			setTaskCompleta(true);
		}

	   

}
