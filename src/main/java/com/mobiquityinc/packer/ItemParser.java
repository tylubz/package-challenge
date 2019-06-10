package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which is responsible for processing items from {@link String} representation to {@link Item} format.
 */
public class ItemParser {

    private static final int MAX_ITEM_PRICE = 100;

    private static final double MAX_ITEM_WEIGHT = 100;

    static final Pattern ITEM_PATTERN = Pattern.compile("\\((\\d+),(\\d{1,3}\\.\\d+),€(\\d{1,3})\\)");

    /**
     * Parses a line containing a representation of a item into an {@link Item} format
     *
     * @param item item {@link String} representation
     * @return {@link Item} class format
     */
    public static Item parse(String item) {
        Matcher itemMatcher = ITEM_PATTERN.matcher(item);
        if (!itemMatcher.find()) {
            throw new APIException("Item structure for " + item + " is not correct." +
                    " Please use next format: (indexNumber,weight,price). For example: (3,4.1,€2).");
        }
        int index = Integer.parseInt(itemMatcher.group(1));
        double weight = Double.parseDouble(itemMatcher.group(2));
        validateItemWeight(weight);
        int price = Integer.parseInt(itemMatcher.group(3));
        validateItemPrice(price);
        return new Item(index, weight, price);
    }

    /**
     * Validates weight of item.
     *
     * @param weight weight of item
     */
    private static void validateItemWeight(double weight) {
        if (weight > MAX_ITEM_WEIGHT) {
            throw new APIException("Max item weight is more than " + MAX_ITEM_WEIGHT + ".");
        }
    }

    /**
     * Validates price of item.
     *
     * @param price price of item
     */
    private static void validateItemPrice(int price) throws APIException {
        if (price > MAX_ITEM_PRICE) {
            throw new APIException("Max item price is more than " + MAX_ITEM_PRICE + ".");
        }
    }
}
