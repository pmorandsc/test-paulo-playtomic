package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.exception.WalletNotFoundException;
import com.playtomic.tests.wallet.repository.WalletRepository;
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

    WalletService walletService;

    @BeforeEach
    public void setUp(){
        walletService = new WalletService(walletRepository);
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
}