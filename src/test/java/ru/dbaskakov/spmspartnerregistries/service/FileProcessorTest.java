package ru.dbaskakov.spmspartnerregistries.service;

import org.apache.catalina.core.ApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.dbaskakov.spmspartnerregistries.mapper.TextMapper;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class FileProcessorTest {
    @Autowired
    private FileProcessor fileProcessor;

    @Test
    void testProcessFile_success() throws Exception {
        String testLine = "extRegId;" + //externalRegistryId
                "2025-05-25T15:45;" + "001;" + "Cashier1;" + "UNI123;" + //start registryData
                "PA456;" + "John Doe;" + "Address 1;" + "2023-05;" + //start counters
                "code1;" + "name1;" + "100;" + "110;" + "!;" + //end counters & start service
                "svcCode1;" + "svcName1;" + "200.50;" + "USD;" + "!;" + //end services
                "15.00;" + "USD;" + "3000.00;" + "USD;" + "222.22;" +
                "USD;" + "5.00;" + "USD;" + //end registryData
                "1111;" + "123.12;" + "RUB;" + "321.32;" + "RUB;" + "777.77;" +
                "RUB;" + "2025-05-26;";

        Path tmpFile = Files.createTempFile("testFile", ".txt");
        Files.writeString(tmpFile, testLine);

        //Start test method
        var result = fileProcessor.processFile(tmpFile.toString());

        //Check main field's
        assertNotNull(result);
        assertEquals("extRegId", result.getExternalRegistryId());
        assertEquals(1, result.getRegistryDataModel().size());

        //Check registryData
        var regData = result.getRegistryDataModel().get(0);
        assertEquals("2025-05-25T15:45", regData.getPaymentDateTime());
        assertEquals("001", regData.getBranchNumber());

        //Check counters
        assertNotNull(regData.getCounters());
        assertEquals(1, regData.getCounters().size());
        assertEquals("code1", regData.getCounters().get(0).getCode());

        //Check services
        assertNotNull(regData.getServices());
        assertEquals(1, regData.getServices().size());
        assertEquals("svcName1", regData.getServices().get(0).getName());

        //Check number's field
        assertEquals(Integer.valueOf(1111), result.getCheckRowRecordRow());
        assertEquals(new BigDecimal("15.00"), regData.getAdditionalInsuranceAmount());

        //Delete temp file
        Files.deleteIfExists(tmpFile);
    }

}