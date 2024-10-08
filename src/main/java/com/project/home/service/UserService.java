package com.project.home.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.UUID;

import com.project.home.config.MyBeanCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.project.home.entity.User;
import com.project.home.enums.Roles;
import com.project.home.repository.UserRepository;

import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Transactional
    public User addUser(User user) throws IOException, InvocationTargetException, IllegalAccessException {
        String path = ResourceUtils.getFile("classpath:static/img").getAbsolutePath();

        if (user.getFile() != null || user.getFile().getBytes().length > 0) {
            byte[] bytes = user.getFile().getBytes();
            String fileName = UUID.randomUUID() + "." + Objects.requireNonNull(user.getFile().getContentType().split("/")[1]);
            Files.write(Paths.get(path + File.separator + fileName), bytes);
            user.setCover(fileName);
        }
        if (user.getPassword() != null)
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        User userExist = getUserByUser(user.getEmail());
        if (userExist == null)
            addRole(user.getEmail(), Roles.USER);

        if (user.getId() != null) {
            MyBeanCopy myBeanCopy = new MyBeanCopy();
            myBeanCopy.copyProperties(userExist, user);
            return repository.save(userExist);
        }
        return repository.save(user);
    }

    @Transactional
    public User changePassword(User user) throws IOException, InvocationTargetException, IllegalAccessException {
        if (user.getPassword() != null)
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        User userExist = getUserById(user.getId());

        MyBeanCopy myBeanCopy = new MyBeanCopy();
        myBeanCopy.copyProperties(userExist, user);
        return repository.save(userExist);

    }

    public User getUserById(Long id) {
        return repository.getById(id);
    }

    public void addRole(String email, Roles role) {
        repository.addRole(email, role);
    }

    public User getUserByUser(String username) {
        return repository.getByEmail(username);
    }

    public Page<User> getAllUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }


    public Page<User> getAllUsersBySearch(User user, Pageable pageable) {
        Page<User> users = repository.findySearch(user, pageable);
        return users;
    }

    public void deleteUser(User user) {
        repository.deleteById(user.getId());

    }
}
