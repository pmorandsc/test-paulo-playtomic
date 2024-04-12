package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.exception.WalletNotFoundException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import com.playtomic.tests.wallet.service.payment.Payment;
import com.playtomic.tests.wallet.service.payment.PaymentService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final PaymentService paymentService;

    public WalletService(WalletRepository walletRepository, PaymentService paymentService){
        this.walletRepository = walletRepository;
        this.paymentService = paymentService;
    }

    public Wallet getWalletById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isEmpty())
            throw new WalletNotFoundException();
        return wallet.get();
    }

    public Wallet createWallet(BigDecimal balance) {
        return walletRepository.save(Wallet.builder().balance(balance).build());
    }

    public Wallet charge(Long id, BigDecimal amount) {
        Optional<Wallet> optionalWallet = walletRepository.findById(id);
        if(optionalWallet.isEmpty())
            throw new WalletNotFoundException();

        Wallet wallet = optionalWallet.get();
        Payment payment = paymentService.charge("123456789", amount);
        if(null != payment){
            wallet.increaseBalance(amount);
            walletRepository.save(wallet);
        }
        return wallet;
    }
}
