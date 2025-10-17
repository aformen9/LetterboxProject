package com.letterbox.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity @Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "uk_users_username", columnNames = "username")
})
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=50)
    private String username;

    @Column(length=100)
    private String displayName;

    @Column(length=120)
    private String email;

    private LocalDateTime createdAt = LocalDateTime.now();


    public Long getId() { return id; }
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String d) { this.displayName = d; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime c) { this.createdAt = c; }
}
