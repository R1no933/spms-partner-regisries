package ru.dbaskakov.spmspartnerregistries.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class RegistryDataModel {
    String paymentDateTime;
    String branchNumber;
    String cashierAtVtbol;
    String uni;
    String personalAccount;
    String payersFullName;
    String address;
    String paymentPeriod;
    List<CountersModel> counters;
    List<ServicesModel> services;
    BigDecimal additionalInsuranceAmount;
    String additionalInsuranceCurrencyCode;
    BigDecimal totalPaymentAmount;
    String totalPaymentCurrencyCode;
    BigDecimal totalTransferAmount;
    String totalTransferCurrencyCode;
    BigDecimal commissionAmount;
    String commissionCurrencyCode;
}
