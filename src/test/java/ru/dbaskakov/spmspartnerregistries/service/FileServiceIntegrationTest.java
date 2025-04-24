package ru.dbaskakov.spmspartnerregistries.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import ru.dbaskakov.spmspartnerregistries.dto.WordDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FileServiceIntegrationTest {
    @Autowired
    private FileService fileService;

    @Test
    void testParseFile() throws Exception {
        String content = String.join(";",
                "word1", "word2", "word3", "word4", "word5", "word6", "word7", "word8", "word9", "word10",
                "word11", "word12", "word13", "word14", "word15", "word16", "word17", "word18", "word19", "word20",
                "word21", "word22", "word23", "word24", "word25", "word26", "word27", "word28", "word29", "word30",
                "word31", "word32", "word33", "word34", "word35", "word36"
        );

        MockMultipartFile file = new MockMultipartFile(
                "file", "test.txt", "text/plain", content.getBytes()
        );

        WordDto result = fileService.parseFile(file);

        assertEquals("word1", result.getWord1());
        assertEquals("word10", result.getWord10());
        assertEquals("word25", result.getWord25());
        assertEquals("word36", result.getWord36());
    }
}
