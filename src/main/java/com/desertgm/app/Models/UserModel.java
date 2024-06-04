package com.desertgm.app.Models;


import com.desertgm.app.Enums.UserRole;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "User")
@CompoundIndexes({
        @CompoundIndex(name = "email_username_idx", def = "{'email': 1, 'username': 1}")
})
public class UserModel implements UserDetails{

    @Id
    @Indexed
    private String id;

    private String password;

    @Indexed
    private String email;

    @Indexed
    private String username;

    private Long supervisorId;

    @Indexed
    private int role;

    private LocalDateTime createdAt;

    public UserModel() {
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

    public UserModel(String password, String email, String username, int role) {
        this.password = password;
        this.email = email;
        this.username = username;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN.getRoleValue()) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
        else return  List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername(){
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }




}
