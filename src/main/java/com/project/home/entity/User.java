package com.project.home.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.home.enums.Roles;
import com.sun.istack.NotNull;

@Entity
public class User implements Serializable {
	
	@Id
	@GeneratedValue
	public Long id;

	@NotBlank(message = "نام کاربر نباید خالی باشد")
	public String name;

	@NotBlank(message = "نام خانوادگی کاربر نباید خالی باشد")
	public String family;
	
	@Email(message = "لطفا مقدار درست را وارد نمایید")
	@NotBlank(message = "ایمیل کاربر نباید خالی باشد")
	@Column(unique = true)
	public String email;

	@NotBlank(message = "شماره همراه کاربر نباید خالی باشد")
	public String mobile;
	
	@Max(value=2)
	public short sex;

	@NotBlank(message = "رمز عبور کاربر نباید خالی باشد")
	public String password;

	@Transient
	public String newPassword;

	@Transient
	public String oldPassword;
	
	@CreationTimestamp
	@Column(name = "created_at",updatable = false)
	public LocalDateTime createdAt;
	
	@UpdateTimestamp
	@Column(name="updated_at")
	public LocalDateTime updatedAt;
	
	@ElementCollection(targetClass = Roles.class)
	@CollectionTable(name="authorities",joinColumns = @JoinColumn(referencedColumnName = "email"))
	@Enumerated(EnumType.STRING)
	public List<Roles> role;
	
	@JsonIgnore
	@Transient
	public MultipartFile file;

	@JsonIgnore
	@Transient
	public boolean isAuthenticated;

	public String cover;
	
	public Boolean enabled=true;
	
	
	@OneToMany(mappedBy = "user")
	private  List<Home> home;

	
	

	public User(Long id, @NotBlank String name, @NotBlank String family, @Email @NotBlank String email,
			@NotBlank String mobile, @Max(1) short sex, @NotBlank String password, LocalDateTime createdAt,
			LocalDateTime updatedAt, List<Roles> role, MultipartFile file, String cover, Boolean enabled,
			List<Home> home) {
		super();
		this.id = id;
		this.name = name;
		this.family = family;
		this.email = email;
		this.mobile = mobile;
		this.sex = sex;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.file = file;
		this.cover = cover;
		this.enabled = enabled;
		this.home = home;
	}


	public User(@NotBlank String name, @NotBlank String family, @Email @NotBlank String email, @NotBlank String mobile,
			@Max(1) short sex, @NotBlank String password, LocalDateTime createdAt, LocalDateTime updatedAt,
			List<Roles> role, MultipartFile file, String cover, Boolean enabled, List<Home> home) {
		super();
		this.name = name;
		this.family = family;
		this.email = email;
		this.mobile = mobile;
		this.sex = sex;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
		this.file = file;
		this.cover = cover;
		this.enabled = enabled;
		this.home = home;
	}



	public User(@NotBlank String name, @NotBlank String family, @Email @NotBlank String email, @NotBlank String mobile,
			@Max(1) short sex, @NotBlank String password,String cover) {
		super();
		this.name = name;
		this.family = family;
		this.email = email;
		this.mobile = mobile;
		this.sex = sex;
		this.password = password;
		this.cover=cover;
	}


	public User() {
		super();
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public short getSex() {
		return sex;
	}

	public void setSex(short sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Roles> getRole() {
		return role;
	}

	public void setRole(List<Roles> role) {
		this.role = role;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Home> getHome() {
		return home;
	}

	public void setHome(List<Home> home) {
		this.home = home;
	}

	public boolean getIsAuthenticated() {
		return isAuthenticated;
	}

	public void setIsAuthenticated(boolean authenticated) {
		isAuthenticated = authenticated;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public boolean isAuthenticated() {
		return isAuthenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		isAuthenticated = authenticated;
	}
}
