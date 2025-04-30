package ru.dbaskakov.spmspartnerregistries.model;

import lombok.Data;

@Data
public class CountersModel {
    String code;
    String name;
    String previousValue;
    String currentValue;
}
