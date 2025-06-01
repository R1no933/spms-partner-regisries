package ru.dbaskakov.spmspartnerregistries.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.dbaskakov.spmspartnerregistries.dto.CountersDTO;
import ru.dbaskakov.spmspartnerregistries.dto.RegistryDataDTO;
import ru.dbaskakov.spmspartnerregistries.dto.ServicesDTO;
import ru.dbaskakov.spmspartnerregistries.dto.TextDTO;
import ru.dbaskakov.spmspartnerregistries.mapper.TextMapper;
import ru.dbaskakov.spmspartnerregistries.model.TextModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class FileProcessor {
    //Add pattern for file name
    private static final Pattern FILE_NAME_PATTERN = Pattern
            .compile("\"^(\\\\d{10}|\\\\d{12})_\\\\d{20}_\\\\d{3}_\\\\d{4}\\\\.txt$\"");
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate;
    private final TextMapper textMapper;

    //Address WireMock (or real API end-point address) from application.yaml
    @Value("${external.api.base-url}")
    private String externalApiBaseUrl;

    /**
     * Reading file, parsing, mapping, serialize to JSON and send POST request
     * @Param filePath - path file
     * @return  HTTP status-code
     */
    public HttpStatusCode processFileAndSendJson(String filePath) throws Exception {
        String fileName = new File(filePath).getName();
        if (!FILE_NAME_PATTERN.matcher(fileName).matches()) {
            throw new IllegalArgumentException("File name doesn't expect file name pattern 10(12)numbers" +
                    "_20numbers_3numbers_MMDD.txt");
        }
        TextDTO textDTO = readAndParseFile(filePath);
        TextModel textModel = textMapper.toModel(textDTO);
        String jsonPayload = objectMapper.writeValueAsString(textModel);

        // Make HTTP request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);

        //Send POST request on WireMock or real API end-point
        ResponseEntity<Void> response = restTemplate.postForEntity(
                externalApiBaseUrl + "/api/data", request, Void.class);

        return response.getStatusCode();
    }

    // --- Method's reading and parsing file ---
    private TextDTO readAndParseFile(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            if (line == null) {
                throw new IllegalArgumentException("Empty file");
            }
            String[] fields = line.split(";", -1);
            if (fields.length != 36) {
                throw new IllegalArgumentException("Expected 36 fields, got: " + fields.length);
            }

            return parseFieldsToTextDTO(fields);
        }
    }

    //Parse lines to DTO
    private TextDTO parseFieldsToTextDTO(String[] fields) {
        TextDTO textDTO = new TextDTO();

        //First field for textDTO
        textDTO.setExternalRegistryId(fields[0]);

        //Create registryDataDTO
        RegistryDataDTO registryDataDTO = new RegistryDataDTO();
        //Parsing fields for registryDataModel(start array registryDataModel)
        registryDataDTO.setPaymentDateTime(fields[1]);
        registryDataDTO.setBranchNumber(fields[2]);
        registryDataDTO.setCashierAtVtbol(fields[3]);
        registryDataDTO.setUni(fields[4]);
        registryDataDTO.setPersonalAccount(fields[5]);
        registryDataDTO.setPayersFullName(fields[6]);
        registryDataDTO.setAddress(fields[7]);
        registryDataDTO.setPaymentPeriod(fields[8]);

        //Parsing fields for counters (start's counters array)
        CountersDTO countersDTO = new CountersDTO();
        countersDTO.setCode(fields[9]);
        countersDTO.setName(fields[10]);
        countersDTO.setPreviousValue(fields[11]);
        countersDTO.setCurrentValue(fields[12]);
        registryDataDTO.setCounters(Collections.singletonList(countersDTO));

        //Parsing fields for service (start's service array)
        ServicesDTO servicesDTO = new ServicesDTO();
        servicesDTO.setCode(fields[14]);
        servicesDTO.setName(fields[15]);
        servicesDTO.setPaySum(parseBigDecimal(fields[16]));
        servicesDTO.setPaySumCurrencyCode(fields[17]);
        registryDataDTO.setServices(Collections.singletonList(servicesDTO));

        //Parsing other fields registryData
        registryDataDTO.setAdditionalInsuranceAmount(parseBigDecimal(fields[19]));
        registryDataDTO.setAdditionalInsuranceCurrencyCode(fields[20]);
        registryDataDTO.setTotalPaymentAmount(parseBigDecimal(fields[21]));
        registryDataDTO.setTotalPaymentCurrencyCode(fields[22]);
        registryDataDTO.setTotalTransferAmount(parseBigDecimal(fields[23]));
        registryDataDTO.setTotalTransferCurrencyCode(fields[24]);
        registryDataDTO.setCommissionAmount(parseBigDecimal(fields[25]));
        registryDataDTO.setCommissionCurrencyCode(fields[26]);
        textDTO.setRegistryDataDTO(Collections.singletonList(registryDataDTO));

        //Continue parse textDTO
        textDTO.setCheckRowRecordRow(parseIntegerSafe(fields[27]));
        textDTO.setCheckRowTotalAmountAcceptedAmount(parseBigDecimal(fields[28]));
        textDTO.setCheckRowTotalAmountAcceptedCurrencyCode(fields[29]);
        textDTO.setCheckRowAmountTransferredToClientAmount(parseBigDecimal(fields[30]));
        textDTO.setCheckRowAmountTransferredToClientCurrencyCode(fields[31]);
        textDTO.setCheckRowBankCommissionAmount(parseBigDecimal(fields[32]));
        textDTO.setCheckRowBankCommissionCurrencyCode(fields[33]);
        textDTO.setCheckRowPaymentOrderNumber(fields[34]);
        textDTO.setCheckRowPaymentDate(fields[35]);

        return textDTO;
    }

    private BigDecimal parseBigDecimal(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }

        try {
            return new BigDecimal(text.trim().replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    // For safe parsing Integer
    private Integer parseIntegerSafe(String text) {
        if (text == null || text.trim().isEmpty()) {
            return null;
        }

        try {
            return Integer.valueOf(text.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
