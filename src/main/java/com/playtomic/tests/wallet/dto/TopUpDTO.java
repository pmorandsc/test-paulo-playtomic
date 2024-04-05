package com.playtomic.tests.wallet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TopUpDTO {
    @NotNull(message = "Amount should not be null")
    @DecimalMin(value = "0.0", message = "Amount should be greater than zero")
    BigDecimal amount;
}
