package com.viceri.todo.domain.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import org.springframework.security.core.GrantedAuthority;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter      
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@SequenceGenerator(name = "seq_role" , sequenceName = "seq_role", allocationSize = 1,initialValue = 1)
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id                                               
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_role")
	@EqualsAndHashCode.Include
	private Long id;
	
	private String nomeRole; 
 
	@Override
	public String getAuthority() { 
		return this.nomeRole;
	}
	
	
}
