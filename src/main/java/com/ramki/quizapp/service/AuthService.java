package com.ramki.quizapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ramki.quizapp.dao.UserRepository;
import com.ramki.quizapp.dto.SignUpDto;
import com.ramki.quizapp.security.User;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByLogin(username);
    }

    public void signUp(SignUpDto data) {
        if (repository.findByLogin(data.login()) != null) {
            throw new RuntimeException("Username already exists");
        }
        String encryptedPassword = passwordEncoder.encode(data.password());
        User newUser = new User(data.login(), encryptedPassword, data.role());
        repository.save(newUser);
    }
}
