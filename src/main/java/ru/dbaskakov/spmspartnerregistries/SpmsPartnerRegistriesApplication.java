package ru.dbaskakov.spmspartnerregistries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.dbaskakov.spmspartnerregistries.configure.FileProcessingProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileProcessingProperties.class)
public class SpmsPartnerRegistriesApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpmsPartnerRegistriesApplication.class, args);
    }
}
