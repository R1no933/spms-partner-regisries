package ru.dbaskakov.spmspartnerregistries.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import ru.dbaskakov.spmspartnerregistries.dto.WordDto;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class FileServiceTest {
    @Autowired
    private FileService fileService;

    @Test
    void testFileProcessing() throws Exception {
        String content = String.join(";", "w1", "w2", "w3", "w4", "w5");
        MockMultipartFile file = new MockMultipartFile(
                "file", "text.txt", "text/plain", content.getBytes());

        WordDto result = fileService.processFile(file);

        assertEquals("w1", result.getWord1());
        assertEquals("w2", result.getWord2());
        assertEquals("w3", result.getWord3());
        assertEquals("w4", result.getWord4());
        assertEquals("w5", result.getWord5());
    }
}
