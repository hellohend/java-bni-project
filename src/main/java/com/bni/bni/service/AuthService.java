package com.bni.bni.service;

import com.bni.bni.entity.User;
import com.bni.bni.repository.UserRepository;
import com.bni.bni.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * AuthService that aligns with the updated "users" table (email_address, is_active, timestamps).
 * <p>
 * Rules:
 *  • Username and e‑mail must be unique.
 *  • Account is enabled only when <code>is_active = TRUE</code>.
 *  • Password is stored ONLY as BCrypt hash (passwordHash).
 *  • created_at / updated_at are handled automatically by Hibernate annotations in {@link User}.
 */
@Service
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository repo,
                       PasswordEncoder encoder,
                       JwtUtil jwtUtil) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Register a new user. Returns a status string for brevity.
     * Ideally you would return a DTO or AuthResponse.
     */
    @Transactional
    public String register(String username, String emailAddress, String rawPassword) {
        if (repo.existsByUsername(username) || repo.existsByEmail(emailAddress)) {
            return "User already exists";
        }

        User user = new User();
        user.setUsername(username);
        user.setEmailAddress(emailAddress);
        user.setPasswordHash(encoder.encode(rawPassword));
        user.setRole("USER");
        // isActive default = true (see User entity)

        repo.save(user);
        return "Registered successfully";
    }

    /**
     * Login with either username or email; returns JWT token if success, otherwise <code>null</code>.
     */
    public String login(String identifier, String rawPassword) {
        Optional<User> userOpt = repo.findByUsername(identifier);
        if (userOpt.isEmpty()) {
            userOpt = repo.findByEmailAddress(identifier);
        }

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (!user.getIsActive()) {
                return null; // account disabled
            }
            if (encoder.matches(rawPassword, user.getPasswordHash())) {
                return jwtUtil.generateToken(user.getUsername(), user.getRole());
            }
        }
        return null;
    }
}
