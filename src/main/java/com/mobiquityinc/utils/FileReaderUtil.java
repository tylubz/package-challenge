package com.mobiquityinc.utils;

import com.mobiquityinc.exception.APIException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Utility class for file reading.
 */
public class FileReaderUtil {

    /**
     * Reads information from specified file.
     *
     * @param filePath path to file
     * @return file content
     */
    public static Stream<String> readFile(String filePath) {
        if (filePath == null) {
            throw new APIException("Path to file is not specified.");
        }
        Path path = Paths.get(filePath);
        if (!Files.exists(path) || Files.isDirectory(path)) {
            throw new APIException("File with specified path doesn't exist.");
        }
        try {
            return Files.lines(path);
        } catch (IOException e) {
            throw new APIException("Unpredictable behavior has happened during file processing.");
        }
    }
}
