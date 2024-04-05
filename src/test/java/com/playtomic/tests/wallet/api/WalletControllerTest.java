package com.playtomic.tests.wallet.api;

import com.playtomic.tests.wallet.entity.Wallet;
import com.playtomic.tests.wallet.service.WalletService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WalletController.class)
class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private WalletService walletService;

    @Test
    public void givenValidIdThenReturnWallet() throws Exception {
        // Given
        when(walletService.getWalletById(1L)).thenReturn(new Wallet(1L, new BigDecimal("5.0")));
        // When
        MvcResult response = mockMvc.perform(get("/wallet/1")).andExpect(status().isOk()).andReturn();
        // Then
        assertThat(response.getResponse().getContentAsString()).contains("1");
        assertThat(response.getResponse().getContentAsString()).contains("5.0");
    }

    @Test
    public void givenValidWalletThenCreateWallet() throws Exception {
        // Given
        when(walletService.createWallet(any(BigDecimal.class))).thenReturn(new Wallet(1L, new BigDecimal("5.0")));
        // When
        MvcResult response = mockMvc
                .perform(post("/wallet").contentType(MediaType.APPLICATION_JSON).content("{\"balance\": 10.0}"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        assertThat(response.getResponse().getContentAsString()).contains("1");
        assertThat(response.getResponse().getContentAsString()).contains("5.0");
    }

    @Test
    public void givenValidTopUpPayloadThenReturnWallet() throws Exception {
        // Given
        when(walletService.charge(any(Long.class), any(BigDecimal.class))).thenReturn(new Wallet(1L, new BigDecimal("5.0")));
        // When
        MvcResult response = mockMvc
                .perform(post("/1/topup").contentType(MediaType.APPLICATION_JSON).content("{\"balance\": 10.0}"))
                .andExpect(status().isOk())
                .andReturn();
        // Then
        assertThat(response.getResponse().getContentAsString()).contains("1");
        assertThat(response.getResponse().getContentAsString()).contains("5.0");
    }

}