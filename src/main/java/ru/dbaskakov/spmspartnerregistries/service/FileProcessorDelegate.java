package ru.dbaskakov.spmspartnerregistries.service;

import org.springframework.http.HttpStatusCode;

import java.io.IOException;

public interface FileProcessorDelegate {
    HttpStatusCode processFileAndSendJson(String filepath) throws Exception;
}
