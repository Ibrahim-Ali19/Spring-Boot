package com.security.jwt.entity;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "user_table")
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Id
    private String userId;

    private String name;
    private String email;
    private String password;
    private String about;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Return an empty collection or your roles/authorities
        return Collections.emptyList(); // or your roles/authorities
    }

    @Override
    public String getUsername() {
        return this.email; // Return the email as the username
    }

    @Override
    public String getPassword() {
        return this.password; // Return the password
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Implement your logic if needed
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Implement your logic if needed
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Implement your logic if needed
    }

    @Override
    public boolean isEnabled() {
        return true; // Implement your logic if needed
    }
}
