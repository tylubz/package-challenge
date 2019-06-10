package com.mobiquityinc.utils;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReaderUtilTest {

    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfFileIsNull() {
        FileReaderUtil.readFile(null);
    }

    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfFileNotExist() {
        FileReaderUtil.readFile("path/to/nowhere");
    }

    @Test()
    public void shouldReadFile() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource("packages.txt").toURI();
        String mainPath = Paths.get(uri).toString();
        Stream<String> stringStream = FileReaderUtil.readFile(mainPath);
        Assert.assertNotNull(stringStream);
    }

}
