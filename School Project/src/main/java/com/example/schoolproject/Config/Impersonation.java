package com.example.schoolproject.Config;

import com.example.schoolproject.Entities.SchoolUser;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@RequiredArgsConstructor
public class Impersonation implements UserDetails {

    private Long id;
    private String userName;
    private String password;

    public static Impersonation fromUser(SchoolUser user) {
        Impersonation impersonation = new Impersonation();

        impersonation.setUserName(user.getUserName());
        impersonation.setId(user.getId());
        impersonation.setPassword(user.getPassword());

        return impersonation;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
