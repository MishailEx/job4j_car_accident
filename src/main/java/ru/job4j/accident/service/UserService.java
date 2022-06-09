package ru.job4j.accident.service;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserService {

    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Object getPrincipalUser() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
