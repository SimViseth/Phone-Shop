package com.example.phoneshop.config.security;

import com.example.phoneshop.entity.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceFakeImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<AppUser> findUserByUsername(String username) {
        List<AppUser> users = List.of(
                new AppUser("seyha", passwordEncoder.encode("seyha678"), RoleEnum.SALE.getAuthorities(),
                        true, true, true, true),
                new AppUser("socheata", passwordEncoder.encode("socheata678"), RoleEnum.ADMIN.getAuthorities(),
                        true, true, true, true)
        );
        return users.stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }
}
