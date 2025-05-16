package ru.dbaskakov.spmspartnerregistries.model;

import lombok.Data;

@Data
public class Counters {
    String code;
    String name;
    String previousValue;
    String currentValue;
}
