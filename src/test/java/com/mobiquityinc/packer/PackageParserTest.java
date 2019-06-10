package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Tests for {@link PackageParser} class.
 */
public class PackageParserTest {

    /**
     * Validates work of {@link PackageParser#parse(String)} method.
     * Should properly parse package to {@link Item} class.
     */
    @Test
    public void shouldParsePackage() {
        List<Item> expectedItemList = Arrays.asList(
                new Item(1, 53.38, 45),
                new Item(2, 88.62, 98),
                new Item(3, 78.48, 3)
        );
        expectedItemList.sort(Comparator.comparingDouble(Item::getWeight));
        Package expectedPackage = new Package(81, expectedItemList);
        String row = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3)";
        Package actualPackage = PackageParser.parse(row);
        Assert.assertEquals(expectedPackage, actualPackage);
    }

    /**
     * Test for validating row structure. Should contain items.
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfStringRowIsNotContainsItems() {
        String row = "81 : ";
        PackageParser.parse(row);
    }

    /**
     * Test for validating row structure. Should contain correct format of item.
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfStringRowContainsIncorrectItems() {
        String row = "81 : (1,53.38,€45";
        PackageParser.parse(row);
    }

    /**
     * Test for checking max weight of package.
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfPackageWeightExceedsMaximum() {
        String row = "155 : (1,53.38,€45)";
        PackageParser.parse(row);
    }

    /**
     * Test for checking max items number in package.
     */
    @Test(expected = APIException.class)
    public void shouldThrowExceptionIfNumberOfItemsExceedsMaximum() {
        String row = "15 : (1,53.38,€45) (2,53.38,€45) (3,53.38,€45) (4,53.38,€45) (5,53.38,€45)" +
                " (6,53.38,€45) (7,53.38,€45) (8,53.38,€45) (9,53.38,€45) (10,53.38,€45)" +
                " (11,53.38,€45) (12,53.38,€45) (13,53.38,€45) (14,53.38,€45) (15,53.38,€45) (16,53.38,€45)";
        PackageParser.parse(row);
    }
}
