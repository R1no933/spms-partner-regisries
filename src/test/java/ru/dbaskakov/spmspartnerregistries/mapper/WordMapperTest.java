package ru.dbaskakov.spmspartnerregistries.mapper;

import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.dbaskakov.spmspartnerregistries.dto.WordDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WordMapperTest {
    private final WordMapper wordMapper = Mappers.getMapper(WordMapper.class);

    @Test
    void testMapToDto() {
        String content = String.join(";",
                "word1", "word2", "word3", "word4", "word5", "word6", "word7", "word8", "word9", "word10",
                "word11", "word12", "word13", "word14", "word15", "word16", "word17", "word18", "word19", "word20",
                "word21", "word22", "word23", "word24", "word25", "word26", "word27", "word28", "word29", "word30",
                "word31", "word32", "word33", "word34", "word35", "word36"
        );

        WordDto dto = wordMapper.mapContentToDto(content);

        assertEquals("word1", dto.getWord1());
        assertEquals("word18", dto.getWord18());
        assertEquals("word36", dto.getWord36());
    }

    @Test
    void testInvalidContent() {
        assertThrows(IllegalArgumentException.class, () -> wordMapper.mapContentToDto("just;one;word"));
    }
}
