package ru.dbaskakov.spmspartnerregistries.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TextModel {
    private String externalRegistryId;
    private List<RegistryDataModel> registryDataModel;
    private Integer checkRowRecordRow;
    private BigDecimal checkRowTotalAmountAcceptedAmount;
    private String checkRowTotalAmountAcceptedCurrencyCode;
    private BigDecimal checkRowAmountTransferredToClientAmount;
    private String checkRowAmountTransferredToClientCurrencyCode;
    private BigDecimal checkRowBankCommissionAmount;
    private String checkRowBankCommissionCurrencyCode;
    private String checkRowPaymentOrderNumber;
    private String checkRowPaymentDate;

}
