package ru.dbaskakov.spmspartnerregistries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dbaskakov.spmspartnerregistries.dto.CountersDTO;
import ru.dbaskakov.spmspartnerregistries.dto.RegistryDataDTO;
import ru.dbaskakov.spmspartnerregistries.dto.ServicesDTO;
import ru.dbaskakov.spmspartnerregistries.model.CountersModel;
import ru.dbaskakov.spmspartnerregistries.model.RegistryDataModel;
import ru.dbaskakov.spmspartnerregistries.model.ServicesModel;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CountersMapper.class, ServicesMapper.class})
public interface RegistryDataMapper {
    RegistryDataMapper INSTANCE = Mappers.getMapper(RegistryDataMapper.class);

    @Mapping(target = "counters", source = "counters", qualifiedByName = "mapCounters")
    @Mapping(target = "services", source = "services", qualifiedByName = "mapServices")
    RegistryDataDTO toDto(RegistryDataModel registryDataModel);

    @Mapping(target = "counters", source = "counters", qualifiedByName = "mapCountersDTOs")
    @Mapping(target = "services", source = "services", qualifiedByName = "mapServicesDTOs")
    RegistryDataModel toModel(RegistryDataDTO registryDataDTO);
}
