package com.example.demo.controller;

import com.example.demo.modal.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/user/")
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

    @PostMapping
    public List<User> saveUser(@RequestBody User user) {
        users.add(user);
        return users;
    }

    @PutMapping
    public User updateUser(@RequestBody User user){
       return users.stream().filter(currentUser -> currentUser.getId() == user.getId()).findFirst().orElseThrow();
    }

    @DeleteMapping("{userId}")
    public List<User> deleteUser(@PathVariable Integer userId){
        users = users.stream().filter(currentUser -> currentUser.getId() != userId).collect(Collectors.toList());
        return users;
    }
}
