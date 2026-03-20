package com.example.phoneshop.config.security;

import com.example.phoneshop.entity.AppUser;
import com.example.phoneshop.entity.Role;
import com.example.phoneshop.entity.Users;
import com.example.phoneshop.exception.ApiException;
import com.example.phoneshop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Primary
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public Optional<AppUser> findUserByUsername(String username) {
        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "User not found"));

        AppUser appUser = AppUser.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                //.authorities(user.getRole().getAuthorities())
                .accountNonExpired(user.isAccountNonExpired())
                .accountNonLocked(user.isAccountNonLocked())
                .credentialsNonExpired(user.isCredentialsNonExpired())
                .enabled(user.isEnabled())
                .build();
        return Optional.ofNullable(appUser);
    }

    private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles) {
        Set<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
                .collect(Collectors.toSet());

        Set<SimpleGrantedAuthority> authoritie = roles.stream()
                .flatMap(role -> toStream(role))
                .collect(Collectors.toSet());
        authorities.addAll(authoritie);

        return authorities;
    }

    private Stream<SimpleGrantedAuthority> toStream(Role role) {
        return role.getPermission().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()));
    }
}
