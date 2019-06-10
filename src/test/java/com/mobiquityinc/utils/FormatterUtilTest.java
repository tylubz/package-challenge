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

public class FormatterUtilTest {

    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void shouldFormatEmptyPackageItems() {
        String expectedString = "-";
        Package pkg = new Package(3, new ArrayList<>());
        String actualString = FormatterUtil.formatPackages(Collections.singletonList(pkg));
        Assert.assertEquals(expectedString, actualString);
    }

    @Test
    public void shouldFormatSeveralPackages() {
        String expectedString = "2,4" + LINE_SEPARATOR + "1,5";
        List<Item> itemList1 =  Arrays.asList(new Item(2, 2.8, 14), new Item(4, 3.2, 11));
        Package pkg1 = new Package(45, itemList1);
        List<Item> itemList2 =  Arrays.asList(new Item(1, 3.8, 15), new Item(5, 5.2, 19));
        Package pkg2 = new Package(55, itemList2);
        String actualString = FormatterUtil.formatPackages(Arrays.asList(pkg1, pkg2));
        Assert.assertEquals(expectedString, actualString);
    }

    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfPackageListIsEmpty() {
        FormatterUtil.formatPackages(Collections.emptyList());
    }

    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfPackageListIsNull() {
        FormatterUtil.formatPackages(null);
    }

    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfItemsListIsNull() {
        Package pkg = new Package(3, null);
        FormatterUtil.formatPackages(Collections.singletonList(pkg));
    }
}
