package com.example.labSpring.Controller;

import com.example.labSpring.User;
import com.example.labSpring.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import com.example.labSpring.UserService.UserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class LabSpringController {

    //Hello World
    @GetMapping("/{input}")
    public String HelloWorld(@PathVariable String input) {
        return new StringBuilder(input).toString();
    }

    //Lab 1
    @GetMapping("/currentTime")
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

        int todayWeek = LocalDateTime.now().getDayOfYear();
        int week = 0;
        if (todayWeek % 7 == 0) {
            week = todayWeek / 7;
        }
        else {
            week = todayWeek / 7 + 1;
        }
        return "Current Time: " + formattedDate + " Week: " + week;
    }

    @GetMapping("/api")
    public List<Integer> getNumbers(@RequestParam(value = "q", defaultValue = "10") int n){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            list.add(i);
        };
        return list;
    }

    @GetMapping("/random_number")
    public int randomNumber(){
        return new Random().nextInt(500)+1;
    }

    @GetMapping("/fib")
    public int getFibonacci(@RequestParam(value = "number", defaultValue = "10") int n) {
        if(n <= 1 ){
            return n;
        }
        return getFibonacci(n-1) + getFibonacci(n-2);
    }

    @GetMapping("/api/{input}")
    public String reverseString(@PathVariable String input) {
        return new StringBuilder(input).reverse().toString();
    }

    //Lab 3
    private final UserService userService;

    //Task 1
    @GetMapping("/api/greeet")
    public String greet() {
        return "Hello World";
    }

    //task 2
    @GetMapping("/api/greet")
    public String greet(@RequestParam( name = "name", defaultValue = "Guest")String name){
        return "Hello " + name + "!";
    }


    //Task 3
    @PostMapping("/api/book")
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
    @PutMapping("/users/{id}")
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


    private UserRepository userRepository;
    @GetMapping("/api//users")
    public Page<User> getUsers(
            @RequestParam(value = "name", required = false, defaultValue = "") String name,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size
    ) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return userRepository.findByNameContaining(name, pageRequest);
    }
}
