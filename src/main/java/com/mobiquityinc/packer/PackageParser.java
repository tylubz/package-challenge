package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static com.mobiquityinc.packer.ItemParser.ITEM_PATTERN;

/**
 * Class which is responsible for processing packages from {@link String} representation to {@link Package} format.
 */
public class PackageParser {

    private static final Pattern PACKAGE_PATTERN = Pattern.compile("(\\d{1,3}) : (.+)");

    private static final String ITEMS_SEPARATOR = " ";

    private static final int MAX_PACKAGE_WEIGHT = 100;

    private static final double MAX_PACKAGE_SIZE = 15;

    /**
     * Parses a line containing a representation of a package into an {@link Package} format.
     *
     * @param row row {@link String} representation
     * @return {@link Package} class format
     */
    public static Package parse(String row) {
        Matcher packageMatcher = PACKAGE_PATTERN.matcher(row);
        if (!packageMatcher.find()) {
            throw new APIException("Package structure for row " + row + " is not correct." +
                    " Please use next format: totalWeight : (item1) (item2).");
        }
        String itemRow = packageMatcher.group(2);
        Matcher itemMatcher = ITEM_PATTERN.matcher(itemRow);
        if (!itemMatcher.find()) {
            throw new APIException("Item structure for row " + itemRow + " is not correct." +
                    " Please use next format: (indexNumber,weight,price). For example: (3,4.1,€2).");
        }
        List<Item> itemList = Arrays.stream(itemRow.split(ITEMS_SEPARATOR))
                .map(ItemParser::parse)
                .sorted(Comparator.comparingDouble(Item::getWeight))
                .collect(Collectors.toList());
        validatePackageSize(itemList.size());
        int weight = Integer.parseInt(packageMatcher.group(1));
        validatePackageWeight(weight);
        return new Package(weight, itemList);
    }

    /**
     * Validates weight of a package.
     *
     * @param weight weight of a package
     */
    private static void validatePackageWeight(int weight) {
        if (weight > MAX_PACKAGE_WEIGHT) {
            throw new APIException("Max package weight is more than " + MAX_PACKAGE_WEIGHT + ".");
        }
    }

    /**
     * Validates size of a package.
     *
     * @param size number of items in a package
     */
    private static void validatePackageSize(int size) {
        if (size > MAX_PACKAGE_SIZE) {
            throw new APIException("The number of items in package is more than " + MAX_PACKAGE_SIZE + ".");
        }
    }
}
