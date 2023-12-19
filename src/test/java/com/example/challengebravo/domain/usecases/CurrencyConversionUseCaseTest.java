package com.example.challengebravo.domain.usecases;

import com.example.challengebravo.domain.exceptions.CurrencyNotFoundException;
import com.example.challengebravo.domain.models.CurrencyExchangeInfo;
import com.example.challengebravo.infrastructure.external.CurrencyExchangeClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CurrencyConversionUseCaseTest {

    @Mock
    private CurrencyExchangeClient currencyExchangeClient;

    @InjectMocks
    private CurrencyConversionUseCase currencyConversionUseCase;

    @Test
    void execute_shouldReturnCurrencyCorrectly() {
        when(currencyExchangeClient.getCurrencyExchangeInfo("USD", "BRL")).thenReturn(
                new CurrencyExchangeInfo(
                        "USD",
                        "BRL",
                        "Dólar Americano/Real Brasileiro",
                        "4.9036",
                        "4.851"
                )
        );

        assertEquals(currencyConversionUseCase.execute("USD", "BRL", new BigDecimal("123.45")), new BigDecimal("602.102685"));
    }

    @Test
    void execure_shouldThrowExceptionWhenCurrencyNotFound() {
        when(currencyExchangeClient.getCurrencyExchangeInfo("USD", "BRL")).thenThrow(new CurrencyNotFoundException("Currency not found"));

        assertThrows(RuntimeException.class, () -> {
            currencyConversionUseCase.execute("USD", "BRL", new BigDecimal("123.45"));
        });
    }
}