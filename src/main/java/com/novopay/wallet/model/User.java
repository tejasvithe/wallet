package com.novopay.wallet.model;


import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends AuditTable implements UserDetails {

    private String email;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String password;

    private String pin;


    @OneToOne(fetch = FetchType.LAZY)
    private Wallet wallet;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
