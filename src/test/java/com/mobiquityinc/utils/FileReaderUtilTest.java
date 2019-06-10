package com.mobiquityinc.utils;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Tests for {@link FileReaderUtil} class.
 */
public class FileReaderUtilTest {

    /**
     * Validates path to file. Should not be null
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfFileIsNull() {
        FileReaderUtil.readFile(null);
    }

    /**
     * Validates path to file. Path should exist
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfFileNotExist() {
        FileReaderUtil.readFile("path/to/nowhere");
    }

    /**
     * Should read information from specified file.
     */
    @Test()
    public void shouldReadFile() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource("packages.txt").toURI();
        String mainPath = Paths.get(uri).toString();
        Stream<String> stringStream = FileReaderUtil.readFile(mainPath);
        Assert.assertNotNull(stringStream);
    }

}
