package com.example.challengebravo.application.services;

import com.example.challengebravo.domain.usecases.CurrencyConversionUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CurrencyService {
    private final CurrencyConversionUseCase currencyConversionUseCase;

    public CurrencyService(CurrencyConversionUseCase currencyConversionUseCase) {
        this.currencyConversionUseCase = currencyConversionUseCase;
    }

    public BigDecimal convertCurrency(String from, String to, String amount) {
        return currencyConversionUseCase.execute(from, to, new BigDecimal(amount));
    }
}
