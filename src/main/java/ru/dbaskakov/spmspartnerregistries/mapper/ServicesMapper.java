package ru.dbaskakov.spmspartnerregistries.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import ru.dbaskakov.spmspartnerregistries.dto.ServicesDTO;
import ru.dbaskakov.spmspartnerregistries.model.ServicesModel;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ServicesMapper {
    ServicesMapper INSTANCE = Mappers.getMapper(ServicesMapper.class);

    @Named("mapServices")
    List<ServicesDTO> mapServices(List<ServicesModel> servicesModel);

    @Named("mapServicesDTOs")
    List<ServicesModel> mapServicesDTOs(List<ServicesDTO> servicesDTO);

    ServicesDTO toDto(ServicesModel servicesModel);
    ServicesModel toModel(ServicesDTO servicesDTO);
}
