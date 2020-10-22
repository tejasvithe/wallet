package com.novopay.wallet.model;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "user")
@Builder
public class User extends AuditTable {

    private String email;

    private String firstName;

    private String lastName;

    private String mobileNumber;

    private String password;

    private String pin;


    @OneToOne
    private Wallet wallet;

}
