package com.ramki.quizapp.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.ramki.quizapp.security.User;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByLogin(String login);
}
