package com.novopay.wallet.controller;

import com.novopay.wallet.dto.ResponseDto;
import com.novopay.wallet.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/wallet")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @PostMapping(value = "/add-money")
    public ResponseDto addMoney(@RequestHeader("token") String token, @RequestParam("wallet-id") String walletId, @RequestParam("amount") String amount, @RequestParam("payment-type") String paymentType) {

        return walletService.addMoneyInWallet(walletId, amount, paymentType);
    }

    @PostMapping(value = "/transfer-money")
    public ResponseDto transferMoney(@RequestHeader("token") String token, @RequestParam("source-wallet-id") String sourceWalletId, @RequestParam("dest-mobile-number") String destMobileNumber, @RequestParam("amount") String amount) {

        return walletService.sendMoneyInWallet(sourceWalletId, destMobileNumber, amount);
    }
}
