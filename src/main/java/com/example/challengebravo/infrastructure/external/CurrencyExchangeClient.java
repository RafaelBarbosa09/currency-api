package com.example.challengebravo.infrastructure.external;

import com.example.challengebravo.domain.exceptions.CurrencyNotFoundException;
import com.example.challengebravo.domain.models.CurrencyExchangeInfo;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class CurrencyExchangeClient {
    private final RestTemplate restTemplate;
    private final String awesomeApiUrl;

    public CurrencyExchangeClient(RestTemplate restTemplate, String awesomeApiUrl) {
        this.restTemplate = restTemplate;
        this.awesomeApiUrl = awesomeApiUrl;
    }

    public CurrencyExchangeInfo getCurrencyExchangeInfo(String from, String to) {
        String url = awesomeApiUrl + from + "-" + to;

        CurrencyExchangeInfo[] currencyExchangeInfoList = restTemplate.getForObject(url, CurrencyExchangeInfo[].class);
        if (currencyExchangeInfoList == null || currencyExchangeInfoList.length == 0) {
            throw new CurrencyNotFoundException("Currency exchange info not found");
        }

        return currencyExchangeInfoList[0];
    }
}
