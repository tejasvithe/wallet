package com.novopay.wallet.service.impl;

import com.novopay.wallet.dto.ResponseDto;
import com.novopay.wallet.model.Transaction;
import com.novopay.wallet.repository.TransactionRepository;
import com.novopay.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransaction(String walletId) {
        List<Transaction> transactionList = transactionRepository.findByWallet_Id(walletId);
        return transactionList;
    }

    @Override
    public ResponseDto reverseTransaction(String transactionId) {

        // reverse logic
        return null;
    }
}
