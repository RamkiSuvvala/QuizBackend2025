package com.ramki.quizapp.dto;

import com.ramki.quizapp.security.UserRole;

public record SignUpDto(String login, String password, UserRole role) {}
