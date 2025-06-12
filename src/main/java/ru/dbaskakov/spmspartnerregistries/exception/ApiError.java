package ru.dbaskakov.spmspartnerregistries.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ApiError {
    private int statusCode;
    private String message;
    private String errorId;
}
