package com.project.home.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.project.home.entity.User;
import com.project.home.enums.Roles;
import com.project.home.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;
	
	
	public User addUser(User user) throws IOException
	{
		String path=ResourceUtils.getFile("classpath:static/img").getAbsolutePath();
		byte[] bytes=user.getFile().getBytes();
		String fileName=UUID.randomUUID()+"."+Objects.requireNonNull(user.getFile().getContentType().split("/")[1]);
		Files.write(Paths.get(path+File.separator+fileName), bytes);
		user.setCover(fileName);
		addRole(user.getEmail(), Roles.USER);
		return repository.save(user);
	}
	
	
	public void addRole(String email,Roles role)
	{
		repository.addRole(email,role);
	}
	
}
