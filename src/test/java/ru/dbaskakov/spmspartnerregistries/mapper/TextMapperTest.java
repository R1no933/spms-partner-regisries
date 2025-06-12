package ru.dbaskakov.spmspartnerregistries.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dbaskakov.spmspartnerregistries.dto.RegistryDataDTO;
import ru.dbaskakov.spmspartnerregistries.dto.TextDTO;
import ru.dbaskakov.spmspartnerregistries.model.TextModel;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TextMapperTest {
    @Autowired
    private TextMapper textMapper;

    @Test
    public void testTextMapper() {
        TextModel textModel = createTextModel();
        TextDTO textDTO = textMapper.toDto(textModel);
        TextModel mappedTextModel = textMapper.toModel(textDTO);

        // Model -> DTO
        assertThat(textDTO)
                .isNotNull()
                .extracting(TextDTO::getExternalRegistryId, TextDTO::getCheckRowAmountTransferredToClientAmount)
                .containsExactly(textModel.getExternalRegistryId(), textModel.getCheckRowAmountTransferredToClientAmount());

        assertThat(textDTO.getRegistryDataDTO())
                .isNotNull()
                .extracting(RegistryDataDTO::getCounters, RegistryDataDTO::getServices)
                .isNotEmpty();

        // DTO -> Model
        assertThat(mappedTextModel)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(textModel);
    }

    private TextModel createTextModel() {
        TextModel textModel = new TextModel();

        textModel.setRegistryDataModel(List.of(new RegistryDataMapperTest().createRegistryDataModel()));
        textModel.setExternalRegistryId("TestRegistryId");
        textModel.setCheckRowAmountTransferredToClientAmount(new BigDecimal("1122.22"));

        return textModel;
    }
}
