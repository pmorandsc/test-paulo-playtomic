package com.playtomic.tests.wallet.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "WALLET")
public class Wallet {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "balance")
    private BigDecimal balance;

    public void increaseBalance(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }
}
