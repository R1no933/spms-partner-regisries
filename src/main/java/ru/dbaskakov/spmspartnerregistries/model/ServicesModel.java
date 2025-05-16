package ru.dbaskakov.spmspartnerregistries.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServicesModel {
    String code;
    String name;
    BigDecimal paySum;
    String paySumCurrencyCode;
}
