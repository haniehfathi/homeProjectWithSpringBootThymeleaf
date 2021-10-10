package com.project.home.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class City {
	@Id
	@GeneratedValue
	public Long id;
	
	@NotBlank
	public String title;
	
	@ManyToOne
	@JsonIgnore
	public Province Province; 
	
	@OneToMany(mappedBy = "city")
	private  List<Home> home;

	public City(Long id, @NotBlank String title, com.project.home.entity.Province province, List<Home> home) {
		super();
		this.id = id;
		this.title = title;
		Province = province;
		this.home = home;
	}

	public City(@NotBlank String title, com.project.home.entity.Province province, List<Home> home) {
		super();
		this.title = title;
		Province = province;
		this.home = home;
	}

	public City() {
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

	public Province getProvince() {
		return Province;
	}

	public void setProvince(Province province) {
		Province = province;
	}

	public List<Home> getHome() {
		return home;
	}

	public void setHome(List<Home> home) {
		this.home = home;
	}
	
	
	

}
