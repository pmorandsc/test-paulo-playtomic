package com.playtomic.tests.wallet.exception;

import com.playtomic.tests.wallet.service.payment.StripeAmountTooSmallException;
import com.playtomic.tests.wallet.service.payment.StripeServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = WalletNotFoundException.class)
    public ResponseEntity<Object> handleWalletNotFoundException(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Wallet not found in database");
    }

    @ExceptionHandler(value = StripeAmountTooSmallException.class)
    public ResponseEntity<Object> handleStripeAmountTooSmallException(){
        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Amount is too small");
    }

    @ExceptionHandler(value = StripeServiceException.class)
    public ResponseEntity<Object> handleStripeServiceException(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error connecting to payments provider");
    }

    @ExceptionHandler(value = ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<Object> handleOptimisticLockingException(){
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error when updating wallet");
    }
}

