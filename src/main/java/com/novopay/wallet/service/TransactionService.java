package com.novopay.wallet.service;

import com.novopay.wallet.dto.ResponseDto;
import com.novopay.wallet.model.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransaction(String walletId);

    Transaction getTransaction(String transactionId);

    ResponseDto reverseTransaction(String transactionId);
}
