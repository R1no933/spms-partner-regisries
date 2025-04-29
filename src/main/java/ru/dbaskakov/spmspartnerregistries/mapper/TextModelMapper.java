package ru.dbaskakov.spmspartnerregistries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dbaskakov.spmspartnerregistries.model.RegistryData;
import ru.dbaskakov.spmspartnerregistries.model.TextModel;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface TextModelMapper {
    TextModelMapper INSTANCE = Mappers.getMapper(TextModelMapper.class);

    @Mapping(target = "registryData", source = "registryDataLines", qualifiedByName = "mapRegistryData")
    TextModel mapToModel(List<String> lines);

    @Named("mapRegistryData")
    default List<RegistryData> mapRegistryData(List<String> registryDataLine) {
        //TODO: релизация будет в парсере
        return null;
    }

    default BigDecimal mapToBigDecimal(String value) {
        return value != null && !value.isEmpty() ? new BigDecimal(value) : null;
    }
}
