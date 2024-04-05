package com.playtomic.tests.wallet.api;

import com.sun.istack.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/wallet")
public class WalletController {
    @GetMapping("/{id}")
    public ResponseEntity<Object> getWalletById(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Object> createWallet(){
        return ResponseEntity.ok().build();
    }
}
