package com.mobiquityinc.utils;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import org.junit.Assert;
import org.junit.Test;
import com.mobiquityinc.model.Package;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests for {@link FormatterUtil} class.
 */
public class FormatterUtilTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Validates work of {@link FormatterUtil#formatPackages(List)} method.
     * Should return "-" if items are not present in package.
     */
    @Test
    public void shouldFormatEmptyPackageItems() {
        String expectedString = "-";
        Package pkg = new Package(3, new ArrayList<>());
        String actualString = FormatterUtil.formatPackages(Collections.singletonList(pkg));
        Assert.assertEquals(expectedString, actualString);
    }

    /**
     * Validates work of {@link FormatterUtil#formatPackages(List)} method.
     * Should format result in {@link String} format.
     */
    @Test
    public void shouldFormatSeveralPackages() {
        String expectedString = "2,4" + LINE_SEPARATOR + "1,5";
        List<Item> itemList1 = Arrays.asList(new Item(2, 2.8, 14), new Item(4, 3.2, 11));
        Package pkg1 = new Package(45, itemList1);
        List<Item> itemList2 = Arrays.asList(new Item(1, 3.8, 15), new Item(5, 5.2, 19));
        Package pkg2 = new Package(55, itemList2);
        String actualString = FormatterUtil.formatPackages(Arrays.asList(pkg1, pkg2));
        Assert.assertEquals(expectedString, actualString);
    }

    /**
     * Validates empty collections.
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfPackageListIsEmpty() {
        FormatterUtil.formatPackages(Collections.emptyList());
    }

    /**
     * Validates null collections.
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfPackageListIsNull() {
        FormatterUtil.formatPackages(null);
    }

    /**
     * Validates null items collection in package.
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfItemsListIsNull() {
        Package pkg = new Package(3, null);
        FormatterUtil.formatPackages(Collections.singletonList(pkg));
    }
}
