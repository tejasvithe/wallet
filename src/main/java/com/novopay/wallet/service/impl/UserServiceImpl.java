package com.novopay.wallet.service.impl;

import com.novopay.wallet.dto.LoginDto;
import com.novopay.wallet.dto.UserDto;
import com.novopay.wallet.exception.InvalidPasswordException;
import com.novopay.wallet.exception.UserAlreadyAvailableException;
import com.novopay.wallet.exception.UserNotFound;
import com.novopay.wallet.model.LoginToken;
import com.novopay.wallet.model.User;
import com.novopay.wallet.model.Wallet;
import com.novopay.wallet.repository.LoginTokenRepository;
import com.novopay.wallet.repository.UserRepository;
import com.novopay.wallet.repository.WalletRepository;
import com.novopay.wallet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginTokenRepository loginTokenRepository;

    @Autowired
    private WalletRepository walletRepository;

    @Override
    public User signUp(UserDto userData) {

        final String encryptedPassword = bCryptPasswordEncoder.encode(userData.getPassword());


        User availableUser = userRepository.findByEmail(userData.getEmail());
        if (availableUser != null) {
            throw new UserAlreadyAvailableException("user already available with same email Id");
        }

        User user = User.builder().firstName(userData.getFirstName()).lastName(userData.getLastName()).password(encryptedPassword).email(userData.getEmail()).build();

        userRepository.save(user);
        Wallet wallet = Wallet.builder().user(user).balance(0.0).build();
        walletRepository.save(wallet);
        return user;
    }

    @Override
    public LoginToken signIn(LoginDto loginData) {
        User user = userRepository.findByEmail(loginData.getUserId());
        if (user == null) {
            throw new UserNotFound("for given userId no user found");
        }
        if (bCryptPasswordEncoder.matches(loginData.getPassword(), user.getPassword())) {
            LoginToken token = new LoginToken(user);
            return loginTokenRepository.save(token);
        } else {
            throw new InvalidPasswordException("Wrong Password");
        }

    }
}
