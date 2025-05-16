package ru.dbaskakov.spmspartnerregistries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.dbaskakov.spmspartnerregistries.dto.TextDTO;
import ru.dbaskakov.spmspartnerregistries.model.TextModel;

@Mapper(componentModel = "spring")
public interface TextMapper {
    TextMapper INSTANCE = Mappers.getMapper(TextMapper.class);

    @Mapping(target = "registryDataDTO", source = "registryDataModel")
    TextDTO toDto(TextModel textModel);
    @Mapping(target = "registryDataModel", source = "registryDataDTO")
    TextModel toModel(TextDTO textDTO);
}
