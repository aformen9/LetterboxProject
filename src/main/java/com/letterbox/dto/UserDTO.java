package com.letterbox.dto;

import jakarta.validation.constraints.NotBlank;

public class UserDTO {
    private Long id;

    @NotBlank
    private String username;
    private String displayName;
    private String email;


    public Long getId() { return id; }
    public void setId(Long i) { this.id = i; }
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String d) { this.displayName = d; }
    public String getEmail() { return email; }
    public void setEmail(String e) { this.email = e; }
}
