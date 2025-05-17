package ru.dbaskakov.spmspartnerregistries.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.dbaskakov.spmspartnerregistries.configure.FileProcessingProperties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileProcessorService {
    private final FileProcessingProperties fileProcessingProperties;
    private Pattern getFilenamePattern() {
        return Pattern.compile(fileProcessingProperties.pattern());
    }

    public void processFiles() {
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(
                Path.of(fileProcessingProperties.inputDir()),
                this::matchesPattern)) {

            for (Path path : stream) {
                try {
                    processFile(path);
                } catch (IOException e) {
                    handleError(path, e);
                }
            }
        } catch (IOException e) {
            log.error("Filed to read directory", e);
        }
    }

    private boolean matchesPattern(Path filePath) {
        String fileName = filePath.getFileName().toString();
        return getFilenamePattern().matcher(fileName).matches();
    }

    private void processFile(Path filePath) throws IOException {
        log.info("Processing file {}", filePath);
        String content = Files.readString(filePath);
        log.debug("Content of file {}", content);
        moveFile(filePath, Path.of(fileProcessingProperties.processedDir()));;
    }

    private void handleError(Path filePath, Exception e) {
        log.error("Error while processing file {}", filePath, e);
        try {
            moveFile(filePath, Path.of(fileProcessingProperties.errorDir()));
        } catch (IOException ex) {
            log.error("Failed to move error file {}", filePath, ex);
        }
    }

    private void moveFile(Path source, Path targetDir) throws IOException {
        Files.createDirectories(targetDir);
        Path targetFile = targetDir.resolve(source.getFileName());
        Files.move(source, targetFile, StandardCopyOption.REPLACE_EXISTING);
    }
}
