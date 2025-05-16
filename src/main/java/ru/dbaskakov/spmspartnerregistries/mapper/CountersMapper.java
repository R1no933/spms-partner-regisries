package ru.dbaskakov.spmspartnerregistries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dbaskakov.spmspartnerregistries.dto.CountersDTO;
import ru.dbaskakov.spmspartnerregistries.model.CountersModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountersMapper {
    CountersMapper INSTANCE = Mappers.getMapper(CountersMapper.class);

    @Named("mapCounters")
    List<CountersDTO> mapCounters(List<CountersModel> countersModel);

    @Named("mapCountersDTOs")
    List<CountersModel> mapCountersDTOs(List<CountersDTO> countersDTO);

    CountersDTO toDto(CountersModel countersModel);
    CountersModel toModel(CountersDTO countersDTO);
}
