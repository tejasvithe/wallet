package com.novopay.wallet.model;

import com.novopay.wallet.enums.PaymentType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "transaction")
@Builder
public class Transaction extends AuditTable {

    private Double amount;

    private String source;

    private String destination;

    private PaymentType paymentType;

    @OneToOne
    private Wallet wallet;
}
