package com.project.home;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.project.home.entity.User;
import com.project.home.repository.UserRepository;
import com.project.home.service.UserService;

@SpringBootApplication
public class HomeApplication  implements CommandLineRunner {
	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(HomeApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		userService.addUser(new User("hanie","fathi","hanieh@gmail.com","0912843",(@Max(1) short) 1,"$2a$10$UrDX6Qo2UZub3S254hJnce46Ni2mbSBaFm4oPpWDmH4VP6jIgM50q"));
//		System.out.print(new BCryptPasswordEncoder().encode("123"));
	}
	

}
