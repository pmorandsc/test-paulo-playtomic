package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.dto.TopUpDTO;
import com.playtomic.tests.wallet.dto.WalletDTO;
import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.service.WalletService;
import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    WalletService walletService;

    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wallet> getWalletById(@PathVariable Long id){
        return ResponseEntity.ok().body(walletService.getWalletById(id));
    }

    @PostMapping
    public ResponseEntity<Wallet> createWallet(@RequestBody @Valid WalletDTO wallet){
        return ResponseEntity.ok().body(walletService.createWallet(wallet.getBalance()));
    }

    @PostMapping("/{id}/topup")
    public ResponseEntity<Wallet> topUp(@PathVariable @NotNull Long id, @Valid @RequestBody TopUpDTO body){
        return ResponseEntity.ok().body(walletService.charge(id, body.getAmount()));
    }
}
