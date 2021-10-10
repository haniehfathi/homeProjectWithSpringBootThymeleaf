package com.project.home.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.home.entity.User;
import com.project.home.enums.Roles;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true,value = "INSERT INTO authorities (user_email,role) VALUES(:#{#email},:#{#role.name()}) ")
	public void addRole(String email,Roles role);

}
