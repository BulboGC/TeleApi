package com.desertgm.app.Models.User;


import com.desertgm.app.Enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "User")
@CompoundIndexes({
        @CompoundIndex(name = "email_username_idx", def = "{'email': 1, 'username': 1}")
})
public class User implements UserDetails{

    @Id
    @Indexed
    private String id;

    @JsonIgnore
    private String password;

    @Indexed(unique = true)
    private String email;

    @Indexed
    private String username;

    private String supervisorId;

    @Indexed
    private int role;

    private String name;

    private String lastname;

    private LocalDateTime createdAt;

    public User() {
        createdAt = LocalDateTime.now();
    }
    public String getRoleAsString() {
        if (role == UserRole.ADMIN.getRoleValue()) {
            return "ADMIN";
        } else if (role == UserRole.USER.getRoleValue()) {
            return "USER";
        }
        else return null;
    }

    public User(String password, String email, String username, int role,String name,String lastname) {
        this.password = password;
        this.email = email;
        this.username = username;
        this.role = role;
        this.createdAt = LocalDateTime.now();
        this.name = name;
        this.lastname = lastname;

    }
    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN.getRoleValue()) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        else return  List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }
    @JsonIgnore
    @Override
    public String getUsername(){
        return username;
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }
    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }
    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }
    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }




}
