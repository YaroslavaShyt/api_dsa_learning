package com.api.api.controllers.user;

import com.api.api.entities.user.Admin;
import com.api.api.entities.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.create(user);
    }

    @GetMapping
    public User getUser(@RequestHeader("X-User-Id") long id) {
        return userService.get(id);
    }

    @GetMapping("/admin")
    public Admin getAdmin(@RequestHeader("X-User-Id") long id) {
        return userService.getAdmin(id);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping
    public User updateUser(@RequestHeader("X-User-Id") Long id, @RequestBody User user) {
        return userService.update(id, user);
    }

    @DeleteMapping
    public void deleteUser(@RequestHeader("X-User-Id") Long id) {
        userService.delete(id);
    }
}
