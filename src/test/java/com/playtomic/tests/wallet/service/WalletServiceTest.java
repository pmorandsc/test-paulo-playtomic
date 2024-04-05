package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.exception.WalletNotFoundException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.payment.Payment;
import com.playtomic.tests.wallet.service.payment.PaymentService;
import com.playtomic.tests.wallet.service.payment.StripeServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WalletServiceTest {
    @Mock
    WalletRepository walletRepository;

    @Mock
    PaymentService paymentService;

    WalletService walletService;

    @BeforeEach
    public void setUp(){
        walletService = new WalletService(walletRepository, paymentService);
    }

    @Test
    public void givenWalletIdThenFindWallet(){
        // Given
        when(walletRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Wallet(1L, new BigDecimal("10.00"))));
        // When
        Wallet result = walletService.getWalletById(1L);
        // Then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getBalance()).isEqualTo(new BigDecimal("10.00"));
    }

    @Test
    public void givenWalletIdThenReturnNull(){
        // Given
        when(walletRepository.findById(any(Long.class))).thenReturn(Optional.empty());
        // When
        Throwable thrown = assertThrows(WalletNotFoundException.class, () -> walletService.getWalletById(1L));
        // Then
        assertThat(thrown).isInstanceOf(WalletNotFoundException.class);
    }

    @Test
    public void givenValidAmountThenReturnUpdatedWallet(){
        // Given
        when(paymentService.charge(any(String.class), any(BigDecimal.class))).thenReturn(new Payment("id"));
        when(walletRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Wallet(1L, new BigDecimal("20.00"))));
        // When
        Wallet result = walletService.charge(1L, new BigDecimal("10.00"));
        // Then
        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getBalance()).isEqualTo(new BigDecimal("30.00"));
    }

    @Test
    public void givenValidAmountThenReturnStripeException(){
        // Given
        when(paymentService.charge(any(String.class), any(BigDecimal.class))).thenThrow(StripeServiceException.class);
        when(walletRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(new Wallet()));
        // When
        Throwable thrown = assertThrows(StripeServiceException.class, () -> walletService.charge(1L, new BigDecimal(10)));
        // Then
        assertThat(thrown).isInstanceOf(StripeServiceException.class);
    }
}