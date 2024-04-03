package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import java.security.Principal;
import java.util.Collection;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("api/admin")
public class AdminController {
    private final UserServiceImpl userServiceImpl;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserServiceImpl userServiceImpl, RoleService roleService) {
        this.userServiceImpl = userServiceImpl;
        this.roleService = roleService;
    }


    @GetMapping
    public List<User> getAllUser() {
        return userServiceImpl.findAll();
    }

    @GetMapping(value = "/getRoles")
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return ResponseEntity.ok(roleService.getAllRole());
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create_user")
    public ResponseEntity<Void> getAddUser(@RequestBody User user) {
        userServiceImpl.saveUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/user/{id}")
    public ResponseEntity<Void> userSaveEdit(@RequestBody User user, @PathVariable Long id) {
        user.setId(id);
        userServiceImpl.updateUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}