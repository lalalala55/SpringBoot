package com.example.demo.controller;

import com.example.demo.modal.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
public class UserController {

    List<User> users = new ArrayList<>(Arrays.asList(
            new User(1, "ram", "r@123"),
            new User(2, "pam", "p@123"),
            new User(3, "sam", "s@123")
            ));

    @GetMapping("{userId}")
    public User getUserById(@PathVariable("userId") Integer userId) {
        return users
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElseThrow();
    }
}
