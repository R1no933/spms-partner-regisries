package ru.dbaskakov.spmspartnerregistries.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.dbaskakov.spmspartnerregistries.dto.WordDto;
import ru.dbaskakov.spmspartnerregistries.service.FileService;

import java.io.IOException;

@RestController
@RequestMapping("/api/parser")
public class FileParserController {
    private final FileService fileService;

    public FileParserController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping(consumes = "multipart/form-data")
    public WordDto parseFile(@RequestPart("file")MultipartFile file) {
        try {
            return fileService.parseFile(file);
        } catch (IOException e) {
            throw new RuntimeException("Processing file exception: ", e);
        }
    }
}
