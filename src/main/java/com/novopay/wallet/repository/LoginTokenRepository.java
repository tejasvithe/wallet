package com.novopay.wallet.repository;

import com.novopay.wallet.model.LoginToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginTokenRepository extends JpaRepository<LoginToken, String> {

    LoginToken findByToken(String token);
}
