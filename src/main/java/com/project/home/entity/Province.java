package com.project.home.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Province {
	
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank
	public String title;
	
	@OneToMany(mappedBy = "province")
	private  List<Home> home;

	@OneToMany(mappedBy = "Province")
	@JsonIgnore
	private  List<City> city;

	public Province(Long id, @NotBlank String title, List<Home> home, List<City> city) {
		super();
		this.id = id;
		this.title = title;
		this.home = home;
		this.city = city;
	}

	public Province(@NotBlank String title, List<Home> home, List<City> city) {
		super();
		this.title = title;
		this.home = home;
		this.city = city;
	}

	public Province() {
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

	public List<City> getCity() {
		return city;
	}

	public void setCity(List<City> city) {
		this.city = city;
	}
	
	
	
	
	
}
