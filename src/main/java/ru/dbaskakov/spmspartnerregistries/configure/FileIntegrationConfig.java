package ru.dbaskakov.spmspartnerregistries.configure;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import ru.dbaskakov.spmspartnerregistries.service.FileProcessorService;


@Configuration
@EnableIntegration
@ConditionalOnProperty(name = "app.file.integration-enabled", havingValue = "true")
public class FileIntegrationConfig {
    @Bean
    public IntegrationFlow fileProcessingFlow(
            FileProcessingProperties properties,
            FileProcessorService service
    ) {

    }
}
