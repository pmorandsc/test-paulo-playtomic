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
public class Wallet {
    @Id
    private Long id;
    private BigDecimal balance;
}
