package com.mobiquityinc.utils;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;

import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * Utility class for formatting objects.
 */
public class FormatterUtil {

    private static final String EMPTY_SIGN = "-";
    private static final String ITEM_SEPARATOR = ",";

    /**
     * Formats packages in single string of package items, separated by line-separator.
     *
     * @param packageList list of packages
     * @return single string of package items
     */
    public static String formatPackages(List<Package> packageList) {
        if (packageList == null || packageList.isEmpty()) {
            throw new APIException("Packages are not present in collection.");
        }
        return packageList.stream()
                .map(Package::getItemList)
                .map(FormatterUtil::formatItems)
                .collect(joining(System.lineSeparator()));
    }

    /**
     * Method helper for formatting items. Transforms list of items to single
     * string with item indexes, separated by commas. If item list is empty returns {@link #EMPTY_SIGN}.
     *
     * @param itemList list of items
     * @return string representation of item indexes, separated by commas
     */
    private static String formatItems(List<Item> itemList) {
        if (itemList == null) {
            throw new APIException("Item collection is null.");
        }
        return itemList.isEmpty() ? EMPTY_SIGN : itemList.stream()
                .map(Item::getIndex)
                .map(String::valueOf)
                .collect(joining(ITEM_SEPARATOR));
    }
}
