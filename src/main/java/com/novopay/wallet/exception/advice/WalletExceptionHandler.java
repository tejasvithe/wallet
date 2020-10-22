package com.novopay.wallet.exception.advice;

import com.novopay.wallet.dto.ResponseDto;
import com.novopay.wallet.exception.InvalidTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WalletExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity InvalidTokenException(InvalidTokenException exp){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setIncorrectFields("token");
        responseDto.setReason(exp.getMessage());
        responseDto.setResultCode("INVALID_TOKEN");
        return ResponseEntity.status(400).body(responseDto);

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity InvalidTokenException(Exception exp){

        ResponseDto responseDto = new ResponseDto();
        responseDto.setIncorrectFields("token");
        responseDto.setReason(exp.getMessage());
        responseDto.setResultCode("INVALID_TOKEN");
        return ResponseEntity.status(400).body(responseDto);

    }
}
