package com.project.home.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank(message = "عنوان نباید خالی باشد")
	public String title;
	
	@ManyToMany(mappedBy = "category")
	@JsonIgnore
	public List<Home> home;

	public Category(Long id, @NotBlank String title, List<Home> home) {
		super();
		this.id = id;
		this.title = title;
		this.home = home;
	}

	public Category(@NotBlank String title, List<Home> home) {
		super();
		this.title = title;
		this.home = home;
	}

	public Category() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Home> getHome() {
		return home;
	}

	public void setHome(List<Home> home) {
		this.home = home;
	}
	 
	
	
	
	

}
