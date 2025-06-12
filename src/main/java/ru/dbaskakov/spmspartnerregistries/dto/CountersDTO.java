package ru.dbaskakov.spmspartnerregistries.dto;

import lombok.Data;

@Data
public class CountersDTO {
    private String code;
    private String name;
    private String previousValue;
    private String currentValue;
}
