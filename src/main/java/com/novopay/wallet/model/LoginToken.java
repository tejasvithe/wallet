package com.novopay.wallet.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "token")
public class LoginToken extends AuditTable{


    private String token;

    @OneToOne
    private User user;

    public LoginToken(User user){
        this.user = user;
        this.token = UUID.randomUUID().toString();
    }
}
