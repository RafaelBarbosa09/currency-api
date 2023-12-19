package com.example.challengebravo.domain.usecases;

import com.example.challengebravo.domain.models.CurrencyExchangeInfo;
import com.example.challengebravo.infrastructure.external.CurrencyExchangeClient;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CurrencyConversionUseCase {
    private final CurrencyExchangeClient currencyExchangeClient;

    public CurrencyConversionUseCase(CurrencyExchangeClient currencyExchangeClient) {
        this.currencyExchangeClient = currencyExchangeClient;
    }

    public BigDecimal execute(String from, String to, BigDecimal amount) {
        CurrencyExchangeInfo currencyExchangeInfo = currencyExchangeClient.getCurrencyExchangeInfo(from, to);
        BigDecimal high = new BigDecimal(currencyExchangeInfo.getHigh());
        BigDecimal low = new BigDecimal(currencyExchangeInfo.getLow());

        return amount.multiply(high.add(low)).divide(new BigDecimal("2"));
    }
}
