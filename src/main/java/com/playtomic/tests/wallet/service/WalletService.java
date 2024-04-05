package com.playtomic.tests.wallet.service;

import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.exception.WalletNotFoundException;
import com.playtomic.tests.wallet.repository.WalletRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WalletService {
    WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository){
        this.walletRepository = walletRepository;
    }

    public Wallet getWalletById(Long id) {
        Optional<Wallet> wallet = walletRepository.findById(id);
        if(wallet.isEmpty())
            throw new WalletNotFoundException();
        return wallet.get();
    }
}
