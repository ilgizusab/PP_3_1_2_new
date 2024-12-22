package ru.kata.pp_3_1_2_new.service;

import ru.kata.pp_3_1_2_new.model.Role;
import ru.kata.pp_3_1_2_new.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    void saveUser(User user);

    void deleteUser(Long id);

    User findByUsername(String username);

    List<Role> getAllRoles();
}
