package com.mobiquityinc.model;

import java.util.List;
import java.util.Objects;

/**
 * Represents package data class
 */
public class Package {

    private final int weight;
    private final List<Item> itemList;

    /**
     * Constructor with parameters.
     *
     * @param weight   weight of package
     * @param itemList list of package items
     */
    public Package(final int weight, final List<Item> itemList) {
        this.weight = weight;
        this.itemList = itemList;
    }

    /**
     * Returns package weight.
     *
     * @return package weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * Returns list of package items.
     *
     * @return list of package items
     */
    public List<Item> getItemList() {
        return itemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return weight == aPackage.weight &&
                Objects.equals(itemList, aPackage.itemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, itemList);
    }
}
