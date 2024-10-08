package com.project.home.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	public User getByEmail(String username);

	@Query("select u from User u  where (  :#{#user.name} is null or u.name like concat('%',:#{#user.name},'%'))" +
			"and ( :#{#user.family} is NULL or u.family like concat('%',:#{#user.family},'%')) " +
			"and ( :#{#user.mobile} is NULL or u.mobile like concat('%',:#{#user.mobile},'%')) " +
			"and ( :#{#user.email} is NULL or u.email like concat('%',:#{#user.email},'%')) ")
    Page<User> findySearch(User user,Pageable pageable);
}
