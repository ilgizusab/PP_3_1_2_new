package ru.kata.pp_3_1_2_new.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.pp_3_1_2_new.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}