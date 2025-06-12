package ru.dbaskakov.spmspartnerregistries.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.dbaskakov.spmspartnerregistries.service.FileProcessor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FileController {
    private final FileProcessor fileProcessor;

    /**
     * Endpoint for processing file on file path
     * parsing and send json on service
     *
     * @param filePath - file path on server
     * @return HTTP status code
     */

    @PostMapping("/process-file")
    public ResponseEntity<String> processFile(@RequestParam String filePath) throws Exception {
        HttpStatusCode statusCode = fileProcessor.processFileAndSendJson(filePath);
        return ResponseEntity.status(statusCode).build();
    }
}
