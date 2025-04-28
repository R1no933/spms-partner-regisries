package ru.dbaskakov.spmspartnerregistries.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TextModel {
    String externalRegistryId;
    List<RegistryData> registryData;
    Integer checkRowRecordRow;
    BigDecimal checkRowTotalAmountAcceptedAmount;
    String checkRowTotalAmountAcceptedCurrencyCode;
    BigDecimal checkRowAmountTransferredToClientAmount;
    String checkRowAmountTransferredToClientCurrencyCode;
    BigDecimal checkRowBankCommissionAmount;
    String checkRowBankCommissionCurrencyCode;
    String checkRowPaymentOrderNumber;
    String checkRowPaymentDate;
}
