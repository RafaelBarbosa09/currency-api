package com.example.challengebravo.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyExchangeInfo {
    private String code;
    private String codein;
    private String name;
    private String high;
    private String low;
}
