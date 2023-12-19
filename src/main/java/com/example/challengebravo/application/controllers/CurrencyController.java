package com.example.challengebravo.application.controllers;

import com.example.challengebravo.application.services.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/convert")
    public ResponseEntity<BigDecimal> convertCurrency(@RequestParam String from, @RequestParam String to, @RequestParam String amount) {
        try {
            BigDecimal convertedAmount = currencyService.convertCurrency(from, to, amount);
            return ResponseEntity.status(HttpStatus.OK).body(convertedAmount);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
