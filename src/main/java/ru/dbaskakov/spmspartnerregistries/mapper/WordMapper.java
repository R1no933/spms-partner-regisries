package ru.dbaskakov.spmspartnerregistries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dbaskakov.spmspartnerregistries.dto.WordDto;


@Mapper(componentModel = "spring")
public interface WordMapper {

    default WordDto arrayToDto(String[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Array is null");
        }

        WordDto dto = new WordDto();
        dto.setWord1(array[0]);
        dto.setWord2(array[1]);
        dto.setWord3(array[2]);
        dto.setWord4(array[3]);
        dto.setWord5(array[4]);

        return dto;
    }
}
