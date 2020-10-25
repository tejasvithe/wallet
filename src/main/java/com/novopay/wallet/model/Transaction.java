package com.novopay.wallet.model;

import com.novopay.wallet.enums.PaymentType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "transaction")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends AuditTable {

    private Double amount;

    private String source;

    private String destination;

    private PaymentType paymentType;
    
    private String walletId;
}
