package com.novopay.wallet.model;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "wallet")
@Builder
public class Wallet extends AuditTable{


    private Double balance;

    @OneToOne
    private User user;
}
