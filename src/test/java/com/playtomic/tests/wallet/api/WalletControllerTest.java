package com.playtomic.tests.wallet.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(WalletController.class)
class WalletControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenValidIdThenReturnWallet() throws Exception {
        // Given

        // When
        mockMvc.perform(get("/wallet/1")).andExpect(status().isOk()).andReturn();

        // Then

    }

    @Test
    public void givenValidWalletThenCreateWallet() throws Exception {
        // Given

        // When
        mockMvc.perform(post("/wallet").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andReturn();

        // Then

    }

}