package ru.dbaskakov.spmspartnerregistries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.dbaskakov.spmspartnerregistries.dto.WordDto;

import java.util.List;


@Mapper(componentModel = "spring")
public interface WordMapper {

    @Mapping(target = ".", source = "content", qualifiedByName = "mapContentToDto")
    WordDto mapToDto(String content);

    @Named("mapContentToDto")
    default WordDto mapContentToDto(String content) {
        if (content == null) {
            return null;
        }

        List<String> words = List.of(content.split(";"));
        if (words.size() != 36) {
            throw new IllegalArgumentException("Content must contain exactly 36 word's");
        }

        WordDto wordDto = new WordDto();
        wordDto.setWord1(words.get(0));
        wordDto.setWord2(words.get(1));
        wordDto.setWord3(words.get(2));
        wordDto.setWord4(words.get(3));
        wordDto.setWord5(words.get(4));
        wordDto.setWord6(words.get(5));
        wordDto.setWord7(words.get(6));
        wordDto.setWord8(words.get(7));
        wordDto.setWord9(words.get(8));
        wordDto.setWord10(words.get(9));
        wordDto.setWord11(words.get(10));
        wordDto.setWord12(words.get(11));
        wordDto.setWord13(words.get(12));
        wordDto.setWord14(words.get(13));
        wordDto.setWord15(words.get(14));
        wordDto.setWord16(words.get(15));
        wordDto.setWord17(words.get(16));
        wordDto.setWord18(words.get(17));
        wordDto.setWord19(words.get(18));
        wordDto.setWord20(words.get(19));
        wordDto.setWord21(words.get(20));
        wordDto.setWord22(words.get(21));
        wordDto.setWord23(words.get(22));
        wordDto.setWord24(words.get(23));
        wordDto.setWord25(words.get(24));
        wordDto.setWord26(words.get(25));
        wordDto.setWord27(words.get(26));
        wordDto.setWord28(words.get(27));
        wordDto.setWord29(words.get(28));
        wordDto.setWord30(words.get(29));
        wordDto.setWord31(words.get(30));
        wordDto.setWord32(words.get(31));
        wordDto.setWord33(words.get(32));
        wordDto.setWord34(words.get(33));
        wordDto.setWord35(words.get(34));
        wordDto.setWord36(words.get(35));


        return wordDto;
    }
}
