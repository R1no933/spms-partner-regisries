package ru.dbaskakov.spmspartnerregistries.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.dbaskakov.spmspartnerregistries.service.FileProcessor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {
    private final FileProcessor fileProcessor;

    /**
     * Endpoint for processing file on file path
     * parsing and send json on service
     *
     * @param filePath - file path on server
     * @return HTTP status code
     */
    @GetMapping("/public")
    public String publicAccess() {
        return "Public Access";
    }

    @GetMapping("/user")
    public String userAccess() {
        return "User is logged in";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin is logged in";
    }

    @PostMapping("/process-file")
    public ResponseEntity<String> processFile(@RequestParam String filePath) throws Exception {
        HttpStatusCode statusCode = fileProcessor.processFileAndSendJson(filePath);
        return ResponseEntity.status(statusCode).build();
    }
}
