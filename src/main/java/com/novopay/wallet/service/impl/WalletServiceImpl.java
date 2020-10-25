package com.novopay.wallet.service.impl;

import com.novopay.wallet.constants.ApplicationConstants;
import com.novopay.wallet.dto.PaymentDetailsDto;
import com.novopay.wallet.dto.ResponseDto;
import com.novopay.wallet.enums.PaymentType;
import com.novopay.wallet.exception.InvalidTransactionException;
import com.novopay.wallet.exception.WalletNotFoundException;
import com.novopay.wallet.model.Transaction;
import com.novopay.wallet.model.User;
import com.novopay.wallet.model.Wallet;
import com.novopay.wallet.payment.factory.PaymentFactory;
import com.novopay.wallet.payment.service.PaymentService;
import com.novopay.wallet.repository.TransactionRepository;
import com.novopay.wallet.repository.UserRepository;
import com.novopay.wallet.repository.WalletRepository;
import com.novopay.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletServiceImpl implements WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private PaymentFactory paymentFactory;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseDto addMoneyInWallet(String walletId, String amount, String paymentType) {
        ResponseDto responseDto = new ResponseDto();
        Optional<Wallet> sourceWallet = walletRepository.findById(walletId);
        if (!sourceWallet.isPresent()) {
            throw new WalletNotFoundException("source Wallet not found");
        }
        PaymentService paymentService = paymentFactory.getPaymentService(paymentType);
        if (paymentService != null) {
            Double balance = Double.parseDouble(amount);
            Double charge = paymentService.calculateCharge(balance);
            Double commission = paymentService.calculateCommission(balance);
            ResponseDto paymentResponse = paymentService.debitMoney(new PaymentDetailsDto(), balance + charge + commission);
            if (paymentResponse.getResultCode().equals(ApplicationConstants.SUCCESS)) {

                Wallet wallet = sourceWallet.get();
                wallet.setBalance(wallet.getBalance() + balance);

                Transaction transaction = Transaction.builder().destination(wallet.getId()).source(paymentResponse.getTransactionId()).paymentType(PaymentType.CARD_PAYMENT).walletId(wallet.getId()).amount(balance).build();

                transactionRepository.save(transaction);
                responseDto.setResultCode("SUCCESS");
                responseDto.setReason("Transfer successfully done");


            } else {
                throw new InvalidTransactionException("Transaction Failed");
            }
        }
        return responseDto;
    }

    @Override
    public ResponseDto sendMoneyInWallet(String sourceWalletId, String destMobileNumber, String amount) {
        ResponseDto responseDto = new ResponseDto();

        Optional<Wallet> sourceWallet = walletRepository.findById(sourceWalletId);
        if (!sourceWallet.isPresent()) {
            throw new WalletNotFoundException("source Wallet not found");
        }

        User user = userRepository.findByMobileNumber(destMobileNumber);
        Optional<Wallet> opDestWallet = walletRepository.findById(user.getWallet().getId());
        if (!opDestWallet.isPresent()) {
            throw new WalletNotFoundException("destination Wallet not found");
        }
        Double balance = Double.parseDouble(amount);
        Wallet wallet = sourceWallet.get();
        wallet.setBalance(wallet.getBalance() - balance);
        Wallet destWallet = opDestWallet.get();
        destWallet.setBalance(destWallet.getBalance() + balance);
        walletRepository.save(destWallet);
        walletRepository.save(wallet);

        Transaction transaction = Transaction.builder().destination(destWallet.getId()).source(wallet.getId()).paymentType(PaymentType.WALLET).walletId(wallet.getId()).amount(balance).build();

        transactionRepository.save(transaction);
        responseDto.setResultCode("SUCCESS");
        responseDto.setReason("Transfer successfully done");


        return responseDto;
    }
}
