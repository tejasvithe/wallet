package com.novopay.wallet.service;

import com.novopay.wallet.dto.LoginDto;
import com.novopay.wallet.dto.UserDto;
import com.novopay.wallet.model.AuthenticationResponse;
import com.novopay.wallet.model.User;

public interface UserService {

    User signUp(UserDto userData);

    AuthenticationResponse signIn(LoginDto loginData) throws Exception;
}
