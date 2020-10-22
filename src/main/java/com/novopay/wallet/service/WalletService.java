package com.novopay.wallet.service;

import com.novopay.wallet.dto.ResponseDto;

public interface WalletService {

    ResponseDto addMoneyInWallet(String walletId, String amount, String paymentType);

    ResponseDto sendMoneyInWallet(String sourceWalletId, String destMobileNumber, String amount);
}
