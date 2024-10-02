package com.example.labSpring.UserService;

import com.example.labSpring.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }
    public List<User> getUsers() {
        return users;
    }
}
