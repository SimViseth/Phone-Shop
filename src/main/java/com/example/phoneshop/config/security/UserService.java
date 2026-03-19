package com.example.phoneshop.config.security;

import com.example.phoneshop.entity.AppUser;

import java.util.Optional;

public interface UserService {
    Optional<AppUser> findUserByUsername(String username);
}
