package com.novopay.wallet.filter;

import com.novopay.wallet.constants.ApplicationConstants;
import com.novopay.wallet.exception.InvalidTokenException;
import com.novopay.wallet.model.LoginToken;
import com.novopay.wallet.repository.LoginTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Component
//@Order(1)
//@WebFilter("/add-money")
public class TransactionFilter implements Filter {

    @Autowired
    private LoginTokenRepository loginTokenRepository;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;

        String token = req.getHeader(ApplicationConstants.TOKEN);

        LoginToken loginToken = loginTokenRepository.findByToken(token);
        if (loginToken == null) {
            throw new InvalidTokenException("Invalid Token");
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
