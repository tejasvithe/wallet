package com.novopay.wallet.service.impl;

import com.novopay.wallet.constants.ApplicationConstants;
import com.novopay.wallet.dto.PaymentDetailsDto;
import com.novopay.wallet.dto.ResponseDto;
import com.novopay.wallet.enums.Status;
import com.novopay.wallet.exception.InvalidTransactionException;
import com.novopay.wallet.model.Transaction;
import com.novopay.wallet.model.Wallet;
import com.novopay.wallet.payment.factory.PaymentFactory;
import com.novopay.wallet.payment.service.PaymentService;
import com.novopay.wallet.repository.TransactionRepository;
import com.novopay.wallet.repository.WalletRepository;
import com.novopay.wallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private PaymentFactory paymentFactory;

    @Override
    public List<Transaction> getAllTransaction(String walletId) {
        List<Transaction> transactionList = transactionRepository.findByWalletId(walletId);
        return transactionList;
    }

    @Override
    public Transaction getTransaction(String transactionId) {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        return transaction.get();
    }

    @Override
    public ResponseDto reverseTransaction(String transactionId) {
        ResponseDto responseDto = new ResponseDto();
        Optional<Transaction> OptionalTransaction = transactionRepository.findById(transactionId);
        Transaction transaction = OptionalTransaction.get();

        PaymentService paymentService = paymentFactory.getPaymentService(transaction.getPaymentType().getValue());

        if (paymentService != null) {
            ResponseDto paymentResponse = paymentService.creditMoney(new PaymentDetailsDto(), transaction.getAmount());

            if (paymentResponse.getResultCode().equals(ApplicationConstants.SUCCESS)) {

                Optional<Wallet> optionalWallet = walletRepository.findById(transaction.getWalletId());
                Wallet wallet = optionalWallet.get();
                wallet.setBalance(wallet.getBalance() - transaction.getAmount());

                walletRepository.save(wallet);

                transaction.setStatus(Status.CANCELLED);
                transactionRepository.save(transaction);


                responseDto.setResultCode("SUCCESS");
                responseDto.setReason("Transfer successfully done");


            } else {
                throw new InvalidTransactionException("Transaction Failed");
            }
        }
        return responseDto;
    }
}
