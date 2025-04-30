package ru.dbaskakov.spmspartnerregistries.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RegistryDataDTO {
    private String paymentDateTime;
    private String branchNumber;
    private String cashierAtVtbol;
    private String uni;
    private String personalAccount;
    private String payersFullName;
    private String address;
    private String paymentPeriod;
    private List<CountersDTO> counters;
    private List<ServicesDTO> services;
    private BigDecimal additionalInsuranceAmount;
    private String additionalInsuranceCurrencyCode;
    private BigDecimal totalPaymentAmount;
    private String totalPaymentCurrencyCode;
    private BigDecimal totalTransferAmount;
    private String totalTransferCurrencyCode;
    private BigDecimal commissionAmount;
    private String commissionCurrencyCode;
}
