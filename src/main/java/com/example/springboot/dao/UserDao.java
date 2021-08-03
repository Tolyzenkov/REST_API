package com.example.springboot.dao;

import org.springframework.stereotype.Component;
import com.example.springboot.model.User;
import java.util.List;

@Component
public interface UserDao {
    void addUser(User user, List<String> roles);

    List<User> getAllUsers();

    User getUserById(long id);

    User getUserByName(String name);

    void updateUser(User user, long id, List<String> role);

    void deleteUser(long id);
}
