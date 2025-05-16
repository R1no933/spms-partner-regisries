package ru.dbaskakov.spmspartnerregistries.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dbaskakov.spmspartnerregistries.dto.CountersDTO;
import ru.dbaskakov.spmspartnerregistries.model.CountersModel;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CountersMapperTest {

    @Autowired
    private CountersMapper countersMapper;

    @Test
    void testCountersMapper() {
        // Init's data for testing
        CountersModel countersModel = createCountersModel();
        CountersDTO countersDTO = countersMapper.toDto(countersModel);
        CountersModel countersMappedModel = countersMapper.toModel(countersDTO);
        List<CountersDTO> countersDTOS = countersMapper.mapCounters(List.of(countersModel));
        List<CountersModel> countersModels = countersMapper.mapCountersDTOs(countersDTOS);

        // Model -> DTO
        assertThat(countersDTO)
                .isNotNull()
                .extracting(CountersDTO::getName, CountersDTO::getCode, CountersDTO::getPreviousValue, CountersDTO::getCurrentValue)
                .containsExactly(countersModel.getName(), countersModel.getCode(), countersModel.getPreviousValue(), countersModel.getCurrentValue());

        // DTO -> model
        assertThat(countersMappedModel)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(countersModel);

        // Test List<> mapping
        assertThat(countersDTOS)
                .isNotEmpty()
                .first()
                .extracting(CountersDTO::getName)
                .isEqualTo(countersDTO.getName());

        assertThat(countersModels)
                .isNotEmpty()
                .first()
                .usingRecursiveComparison()
                .isEqualTo(countersModel);

    }

    public CountersModel createCountersModel() {
        CountersModel countersModel = new CountersModel();
        countersModel.setCode("TestCode");
        countersModel.setName("TestName");
        countersModel.setCurrentValue("TestCurrentValue");
        countersModel.setPreviousValue("TestPreviousValue");
        return countersModel;
    }
}
