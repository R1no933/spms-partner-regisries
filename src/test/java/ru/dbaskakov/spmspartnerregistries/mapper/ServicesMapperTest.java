package ru.dbaskakov.spmspartnerregistries.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dbaskakov.spmspartnerregistries.dto.CountersDTO;
import ru.dbaskakov.spmspartnerregistries.dto.ServicesDTO;
import ru.dbaskakov.spmspartnerregistries.model.ServicesModel;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ServicesMapperTest {
    @Autowired
    private ServicesMapper servicesMapper;

    @Test
    void testServicesMapper() {
        ServicesModel servicesModel = createServicesModel();
        ServicesDTO servicesDTO = servicesMapper.toDto(servicesModel);
        ServicesModel servicesMappedModels = servicesMapper.toModel(servicesDTO);
        List<ServicesDTO> servicesDTOS = servicesMapper.mapServices(List.of(servicesModel));
        List<ServicesModel> servicesModels = servicesMapper.mapServicesDTOs(servicesDTOS);

        // Model -> DTO
        assertThat(servicesDTO)
                .isNotNull()
                .extracting(ServicesDTO::getName, ServicesDTO::getCode, ServicesDTO::getPaySum, ServicesDTO::getPaySumCurrencyCode)
                .containsExactly(servicesModel.getName(), servicesModel.getCode(), servicesModel.getPaySum(), servicesModel.getPaySumCurrencyCode());

        // DTO -> model
        assertThat(servicesMappedModels)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(servicesModel);

        // Test List<> mapping
        assertThat(servicesDTOS)
                .isNotEmpty()
                .first()
                .extracting(ServicesDTO::getName)
                .isEqualTo(servicesDTO.getName());

        assertThat(servicesModels)
                .isNotEmpty()
                .first()
                .usingRecursiveComparison()
                .isEqualTo(servicesModel);
    }

    public ServicesModel createServicesModel() {
        ServicesModel servicesModel = new ServicesModel();
        servicesModel.setCode("TestCode");
        servicesModel.setName("TestName");
        servicesModel.setPaySum(new BigDecimal("100.00"));
        servicesModel.setPaySumCurrencyCode("TestPaySumCurrencyCode");
        return servicesModel;
    }
}
