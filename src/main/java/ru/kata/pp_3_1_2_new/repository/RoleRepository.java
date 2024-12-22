package ru.kata.pp_3_1_2_new.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kata.pp_3_1_2_new.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}