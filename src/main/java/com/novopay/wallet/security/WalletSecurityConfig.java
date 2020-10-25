package com.novopay.wallet.security;


import com.novopay.wallet.filter.TransactionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WalletSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private TransactionFilter transactionFilter;

    @Autowired
    private UserDetailsService myUserDetailsService;



   /* @Bean
    public FilterRegistrationBean<TransactionFilter> loggingFilter() {
        FilterRegistrationBean<TransactionFilter> registrationBean
                = new FilterRegistrationBean<>();

        registrationBean.setFilter(new TransactionFilter());
        registrationBean.addUrlPatterns("/wallet/*");

        return registrationBean;
    }*/

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/login/*").permitAll().
                anyRequest().authenticated().and().
                exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(transactionFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
