package com.example.labSpring.Controller;

import com.example.labSpring.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.labSpring.UserService.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LabSpringController {

    private final UserService userService;

    //Task 1
    @GetMapping("/greeet")
    public String greet() {
        return "Hello World";
    }

    //task 2
    @GetMapping("/greet")
    public String greet(@RequestParam( name = "name", defaultValue = "Guest")String name){
        return "Hello " + name + "!";
    }


    //Task 3
    @PostMapping("/book")
    public Map<String, Object> addBook(@RequestBody Map<String, Object> book) {
        book.put("status", "received");
        return book;
    }


    //Task 4
    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") long id ) {
        return "User with id " + id + " found";
    }

    //Task 5
    @PutMapping("/user/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable("id") long id,
            @RequestBody Map<String, Object> user) {

        String newName = (String) user.get("name");
        int newAge = (int) user.get("age");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User with id " + id + " updated");
        response.put("name", newName);
        response.put("age", newAge);

        return ResponseEntity.ok(response);
    }

    //Task 6
    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        return ResponseEntity.ok("User with ID " + id + " has been deleted.");
    }

    //Task 7
    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody User user){
        userService.addUser(user);
        return ResponseEntity.ok("User created " + user.getName() + " " + "user age " + user.getAge());
    }

    //Task 9
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getUsers();
        return ResponseEntity.ok(userList);
    }
}
