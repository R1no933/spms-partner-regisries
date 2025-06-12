package ru.dbaskakov.spmspartnerregistries.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.verification.LoggedRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;

import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


@SpringBootTest
@EnableWireMock({@ConfigureWireMock(port = 0)})
@ActiveProfiles("test") // For application-test.yaml
public class FileProcessorWireMockIntegrationTest {
    @Autowired
    private FileProcessor fileProcessor;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void simpleStubTest() {
        stubFor(get(urlEqualTo("/test"))
                .willReturn(aResponse().withStatus(200)));
    }

    @Test
    void testProcessFileAndSendJson_success() throws Exception {
        // Configure WireMock: expect: Method - POST on /api/data and response 204 status code
        stubFor(post(urlEqualTo("/api/data"))
                .withHeader("Content-Type", equalTo("application/json"))
                .willReturn(noContent()));

        // Test file path
        String testFilePath = getClass().getClassLoader().getResource("testFile.txt").getFile();

        var status = fileProcessor.processFileAndSendJson(testFilePath);
        assertEquals(204, status.value());

        List<LoggedRequest> requestList = WireMock.findAll(postRequestedFor(urlEqualTo("/api/data")));
        for (LoggedRequest loggedRequest : requestList) {
            String body = loggedRequest.getBodyAsString();
            JsonNode jsonNode = new ObjectMapper().readTree(body);
            String prettyJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            System.out.println("WireMock received JSON request body: " + prettyJson);
        }

        // Check for wiremock get request
        verify(postRequestedFor(urlEqualTo("/api/data"))
                .withHeader("Content-Type", equalTo("application/json")));
    }
}
