package ru.dbaskakov.spmspartnerregistries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dbaskakov.spmspartnerregistries.dto.CountersDTO;
import ru.dbaskakov.spmspartnerregistries.dto.RegistryDataDTO;
import ru.dbaskakov.spmspartnerregistries.dto.ServicesDTO;
import ru.dbaskakov.spmspartnerregistries.dto.TextDTO;
import ru.dbaskakov.spmspartnerregistries.model.CountersModel;
import ru.dbaskakov.spmspartnerregistries.model.RegistryDataModel;
import ru.dbaskakov.spmspartnerregistries.model.ServicesModel;
import ru.dbaskakov.spmspartnerregistries.model.TextModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistryMapper {
    RegistryMapper INSTANCE = Mappers.getMapper(RegistryMapper.class);

    //TextModel mapping
    @Mapping(target = "registryDataDTO", source = "registryDataModel")
    TextDTO toDto(TextModel textModel);
    @Mapping(target = "registryDataModel", source = "registryDataDTO")
    TextModel toModel(TextDTO textDTO);

    //RegistryDataModel mapping
    @Mapping(target = "counters", source = "counters", qualifiedByName = "mapCounters")
    @Mapping(target = "services", source = "services", qualifiedByName = "mapServices")
    RegistryDataDTO toDto(RegistryDataModel registryDataModel);

    @Mapping(target = "counters", source = "counters", qualifiedByName = "mapCountersDTOs")
    @Mapping(target = "services", source = "services", qualifiedByName = "mapServicesDTOs")
    RegistryDataModel toModel(RegistryDataDTO registryDataDTO);

    //Counters mapping
    @Named("mapCounters")
    List<CountersDTO> mapCounters(List<CountersModel> countersModel);

    @Named("mapCountersDTOs")
    List<CountersModel> mapCountersDTOs(List<CountersDTO> countersDTO);

    CountersDTO toDto(CountersModel countersModel);
    CountersModel toModel(CountersDTO countersDTO);

    //Services mapping
    @Named("mapServices")
    List<ServicesDTO> mapServices(List<ServicesModel> servicesModel);

    @Named("mapServicesDTOs")
    List<ServicesModel> mapServicesDTOs(List<ServicesDTO> servicesDTO);

    ServicesDTO toDto(ServicesModel servicesModel);
    ServicesModel toModel(ServicesDTO servicesDTO);
}
