package ru.dbaskakov.spmspartnerregistries.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicesDTO {
    private String code;
    private String name;
    private BigDecimal paySum;
    private String paySumCurrencyCode;
}
