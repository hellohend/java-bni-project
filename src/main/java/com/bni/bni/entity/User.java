package com.bni.bni.entity;

import java.time.OffsetDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Entity class that mirrors the current "users" table structure
 * ────────────────────────────────────────────────────────────────
 * id            : bigint  PK, auto‑increment (users_id_seq)
 * username      : varchar(255) UNIQUE, NOT NULL
 * email_address : varchar(255)
 * password      : varchar(255)   (optionally store plain, better keep NULL)
 * password_hash : varchar(255)
 * role          : varchar(255)
 * is_active     : boolean         DEFAULT TRUE
 * created_at    : timestamptz     DEFAULT now()
 * updated_at    : timestamptz     DEFAULT now()
 */
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(name = "email_address", length = 255)
    private String emailAddress;

    /**
     * Storing the raw password directly is generally discouraged.
     * Keep it optional (nullable=true) so you can ignore it and only
     * use passwordHash if desired.
     */
    @Column(length = 255)
    private String password;

    @Column(name = "password_hash", length = 255)
    private String passwordHash;

    @Column(length = 255)
    private String role;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "timestamptz default now()")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, columnDefinition = "timestamptz default now()")
    private OffsetDateTime updatedAt;

    // ────────────────────────────────────────────────────────────
    // Constructors
    // ────────────────────────────────────────────────────────────
    public User() {
    }

    public User(String username,
                String emailAddress,
                String password,
                String passwordHash,
                String role,
                Boolean isActive) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.password = password;
        this.passwordHash = passwordHash;
        this.role = role;
        this.isActive = isActive != null ? isActive : true;
    }

    // ────────────────────────────────────────────────────────────
    // Getters & Setters
    // ────────────────────────────────────────────────────────────

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(OffsetDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
