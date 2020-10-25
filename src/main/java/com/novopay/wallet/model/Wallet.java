package com.novopay.wallet.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "wallet")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Wallet extends AuditTable {


    private Double balance;

   
}
