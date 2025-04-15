package ru.dbaskakov.spmspartnerregistries.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dbaskakov.spmspartnerregistries.dto.WordDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class WordMapperTest {
    @Autowired
    private WordMapper wordMapper;

    @Test
    void testArrayMapping() {
        String[] words = new String[5];
        for (int i = 0; i < 5; i++) {
            words[i] = "word" + (i + 1);
        }

        WordDto dto = wordMapper.arrayToDto(words);

        assertEquals("word1", dto.getWord1());
        assertEquals("word2", dto.getWord2());
        assertEquals("word3", dto.getWord3());
        assertEquals("word4", dto.getWord4());
        assertEquals("word5", dto.getWord5());
    }
}
