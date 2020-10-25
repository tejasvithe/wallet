package com.novopay.wallet.controller;

import com.novopay.wallet.dto.ResponseDto;
import com.novopay.wallet.model.Transaction;
import com.novopay.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/wallet")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping(value = "/get-all-transaction")
    public List<Transaction> getAllTransaction(@RequestParam("wallet-id") String walletId) {

        return transactionService.getAllTransaction(walletId);
    }

    @PostMapping(value = "/reverse-transaction")
    public ResponseDto reverseTransaction(@RequestParam("transaction-id") String transactionId) {

        return transactionService.reverseTransaction(transactionId);

    }
}
