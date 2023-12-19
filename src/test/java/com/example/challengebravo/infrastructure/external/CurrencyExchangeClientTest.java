package com.example.challengebravo.infrastructure.external;

import com.example.challengebravo.domain.exceptions.CurrencyNotFoundException;
import com.example.challengebravo.domain.models.CurrencyExchangeInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyExchangeClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyExchangeClient currencyExchangeClient;


    @Test
    void getCurrencyExchangeInfo_shouldReturnExchangeCorrectly() {
        when(restTemplate.getForObject(anyString(), eq(CurrencyExchangeInfo[].class))).thenReturn(
                new CurrencyExchangeInfo[] {
                        new CurrencyExchangeInfo(
                                "USD",
                                "BRL",
                                "Dólar Americano/Real Brasileiro",
                                "4.9036",
                                "4.851"
                        )
                }
        );

        assertDoesNotThrow(() -> {
            currencyExchangeClient.getCurrencyExchangeInfo("USD", "BRL");
        });
    }

    @Test
    void getCurrencyExchangeInfo_shouldThrowExceptionWhenCurrencyNotFound() {
        when(restTemplate.getForObject(anyString(), eq(CurrencyExchangeInfo[].class))).thenReturn(
                new CurrencyExchangeInfo[] {}
        );

        assertThrows(CurrencyNotFoundException.class, () -> {
            currencyExchangeClient.getCurrencyExchangeInfo("USD", "BRL");
        });
    }
}