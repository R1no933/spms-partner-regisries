package ru.dbaskakov.spmspartnerregistries.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dbaskakov.spmspartnerregistries.dto.CountersDTO;
import ru.dbaskakov.spmspartnerregistries.dto.RegistryDataDTO;
import ru.dbaskakov.spmspartnerregistries.dto.ServicesDTO;
import ru.dbaskakov.spmspartnerregistries.model.RegistryDataModel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RegistryDataMapperTest {
    @Autowired
    private RegistryDataMapper registryDataMapper;

    @Test
    void testRegistryDataMapper() {
        RegistryDataModel registryDataModel = createRegistryDataModel();
        RegistryDataDTO registryDataDTO = registryDataMapper.toDto(registryDataModel);
        RegistryDataModel registryDataMappedModel = registryDataMapper.toModel(registryDataDTO);

        // Model -> DTO
        assertThat(registryDataDTO)
                .isNotNull()
                .satisfies(rd -> {
                    assertThat(rd.getCounters())
                            .isNotEmpty()
                            .first()
                            .extracting(CountersDTO::getCode)
                            .isEqualTo(registryDataModel.getCounters().get(0).getCode());

                    assertThat(rd.getServices())
                            .isNotEmpty()
                            .first()
                            .extracting(ServicesDTO::getCode)
                            .isEqualTo(registryDataModel.getServices().get(0).getCode());
                });

        // DTO -> Model
        assertThat(registryDataMappedModel)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(registryDataModel);
    }

    public RegistryDataModel createRegistryDataModel() {
        RegistryDataModel registryDataModel = new RegistryDataModel();

        registryDataModel.setCounters(List.of(new CountersMapperTest().createCountersModel()));
        registryDataModel.setServices(List.of(new ServicesMapperTest().createServicesModel()));

        return registryDataModel;
    }
}
