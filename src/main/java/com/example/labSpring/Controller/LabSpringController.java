package com.example.labSpring.Controller;

import com.example.labSpring.Entity.LabSpringEntity;
import com.example.labSpring.LabSpringService.LabSpringService;
import com.example.labSpring.Repository.LabSpringRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class LabSpringController {

    private final LabSpringService labSpringService;
    private final LabSpringRepository labSpringRepository;

    @GetMapping("/{input}")
    public String helloWorld(@PathVariable String input) {
        return "Hello WOrld" + new StringBuilder(input).toString();
    }

    @GetMapping("/currentTime")
    public String getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        String formattedDate = now.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));

        int todayWeek = LocalDateTime.now().getDayOfYear();
        int week = (todayWeek % 7 == 0) ? todayWeek / 7 : todayWeek / 7 + 1;

        return "Current Time: " + formattedDate + " Week: " + week;
    }

    @GetMapping("/api")
    public List<Integer> getNumbers(@RequestParam(value = "q", defaultValue = "10") int n) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        return list;
    }

    @GetMapping("/random_number")
    public int randomNumber() {
        return new Random().nextInt(500) + 1;
    }

    @GetMapping("/fib")
    public int getFibonacci(@RequestParam(value = "number", defaultValue = "10") int n) {
        return (n <= 1) ? n : getFibonacci(n - 1) + getFibonacci(n - 2);
    }

    @GetMapping("/api/{input}")
    public String reverseString(@PathVariable String input) {
        return new StringBuilder(input).reverse().toString();
    }

    @GetMapping("/api/greeet")
    public String greet() {
        return "Hello World";
    }

    @GetMapping("/api/greet")
    public String greet(@RequestParam(name = "name", defaultValue = "Guest") String name) {
        return "Hello " + name + "!";
    }

    @PostMapping("/api/books/book")
    public Map<String, Object> addBook(@RequestBody Map<String, Object> book) {
        book.put("status", "received");
        return book;
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") long id) {
        return "User with id " + id + " found";
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<Map<String, Object>> updateUser(
            @PathVariable("id") long id,
            @RequestBody Map<String, Object> user) {

        String newName = (String) user.get("name");
        String newSurname = (String) user.get("sur_name");
        int newAge = (int) user.get("age");

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User with id " + id + " updated");
        response.put("name", newName);
        response.put("sur_name", newSurname);
        response.put("age", newAge);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") long id) {
        labSpringService.deleteLabSpring(id);
        return ResponseEntity.ok("User with ID " + id + " has been deleted.");
    }

    @PostMapping("/user")
    public ResponseEntity<String> createUser(@Valid @RequestBody LabSpringEntity labSpring) {
        labSpringService.createLabSpring(labSpring);
        return ResponseEntity.ok("User created " + labSpring.getName() + labSpring.getSurName() + " user age " + labSpring.getAge());
    }

    @GetMapping("/users")
    public ResponseEntity<List<LabSpringEntity>> getAllUsers() {
        List<LabSpringEntity> userList = labSpringService.getAllLabSpring();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/api/pagin")
    public Page<LabSpringEntity> getUsers(
            @RequestParam(value = "offset", defaultValue = "0") Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit
    )
    {
        return labSpringRepository.findAll(PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC,"name")));
    }
}
