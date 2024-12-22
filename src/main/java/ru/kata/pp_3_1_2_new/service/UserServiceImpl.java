package ru.kata.pp_3_1_2_new.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.pp_3_1_2_new.model.Role;
import ru.kata.pp_3_1_2_new.model.User;
import ru.kata.pp_3_1_2_new.repository.RoleRepository;
import ru.kata.pp_3_1_2_new.repository.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        User exUser = userRepository.findByUsername(user.getUsername());
        if (exUser == null) {
            exUser = new User();
        }
        exUser.setFirstName(user.getFirstName());
        exUser.setLastName(user.getLastName());
        exUser.setEmail(user.getEmail());
        if (!user.getPassword().isEmpty()) {
            exUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        exUser.setRoles(user.getRoles());
        userRepository.save(exUser);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}