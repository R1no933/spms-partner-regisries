package ru.dbaskakov.spmspartnerregistries.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.dbaskakov.spmspartnerregistries.dto.WordDto;
import ru.dbaskakov.spmspartnerregistries.mapper.WordMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class FileService {
    private final WordMapper wordMapper;

    public FileService(WordMapper wordMapper) {
        this.wordMapper = wordMapper;
    }

    public WordDto processFile(MultipartFile file) throws IOException {
        String content = readFile(file);
        return convertToDto(content);
    }

    private String readFile(MultipartFile file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return reader.readLine();
        }
    }

    private WordDto convertToDto(String content) {
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("File is empty");
        }

        String[] words = content.split(";");

        return wordMapper.arrayToDto(words);
    }

}
