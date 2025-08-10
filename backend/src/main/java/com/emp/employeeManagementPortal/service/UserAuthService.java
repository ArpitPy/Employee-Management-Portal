package com.emp.employeeManagementPortal.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * This is the Service file for managing user authentication data.
 * and Registration rules/criteria.
 */
@Service
public class UserAuthService {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public UserAuthService(JdbcTemplate jdbcTemplate, PasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }
    // Check if user Exists
    public boolean userExists(int userId) {
        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM UserAuth WHERE userId = ?",
                Integer.class,
                userId
        );
        return count != null && count > 0;
    }

    // Registers the user
    public void registerUser(int userId, String email, String rawPassword, int projectId) {
        if (userExists(userId)) {
            throw new RuntimeException("User Already Exists");
        }
        if(rawPassword.isBlank()){
            throw new RuntimeException("Password cannot be blank");
        }
        if(rawPassword.length() < 8){
            throw new RuntimeException("Password must be at least 8 characters long");
        }
        if (!rawPassword.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).+$")) {
            throw new RuntimeException("Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character");
        }

        String encoded = passwordEncoder.encode(rawPassword);
        jdbcTemplate.update(
                "INSERT INTO UserAuth(userId, password, email, projectId) VALUES (?, ?, ?, ?)",
                userId, encoded, email, projectId
        );
    }

    public String getEncodedPasswordOrNull(int userId) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT password FROM UserAuth WHERE userId = ?",
                    String.class,
                    userId
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }
}
