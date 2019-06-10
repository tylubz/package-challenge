package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

/**
 * Tests for {@link Packer} class.
 */
public class PackerTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Validates work of {@link Packer#pack(String)} method.
     * Should properly process data from file and return result in {@link String} format.
     *
     * @throws URISyntaxException if file cannot be found.
     */
    @Test
    public void shouldPackPackages() throws URISyntaxException {
        String expectedResult = "4" + LINE_SEPARATOR + "-" + LINE_SEPARATOR + "2,7" + LINE_SEPARATOR + "8,9";
        URI uri = ClassLoader.getSystemResource("packages.txt").toURI();
        String pathToFile = Paths.get(uri).toString();
        String actualResult = Packer.pack(pathToFile);
        Assert.assertEquals(expectedResult, actualResult);
    }

    /**
     * Should checks file content and throw exception if data format is not correct.
     *
     * @throws URISyntaxException if file cannot be found
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfDataIncorrect() throws URISyntaxException {
        URI uri = ClassLoader.getSystemResource("corrupted_data.txt").toURI();
        String pathToFile = Paths.get(uri).toString();
        Packer.pack(pathToFile);
    }
}
