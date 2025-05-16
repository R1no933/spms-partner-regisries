package ru.dbaskakov.spmspartnerregistries.configure;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app.file")
public record FileProcessingProperties(
        @NotBlank String inputDir,
        @NotBlank String processedDir,
        @NotBlank String errorDir,
        @Pattern(regexp = "^\\^,*\\$$") String pattern,
        long polingInterval
) {
}
