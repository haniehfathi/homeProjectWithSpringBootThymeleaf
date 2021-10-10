package com.project.home.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class HomePictures {
	
	@Id
	@GeneratedValue
	public Long id;
	
	public String image;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore
	public Home home;

	public HomePictures(Long id, String image, Home home) {
		super();
		this.id = id;
		this.image = image;
		this.home = home;
	}

	public HomePictures(String image, Home home) {
		super();
		this.image = image;
		this.home = home;
	}
	
	

	public HomePictures(Long id, String image) {
		super();
		this.id = id;
		this.image = image;
	}

	public HomePictures() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Home getHome() {
		return home;
	}

	public void setHome(Home home) {
		this.home = home;
	}
	
	
	

}
