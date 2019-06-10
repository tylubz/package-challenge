package com.mobiquityinc.model;

import java.util.Objects;

/**
 * Represents item data class
 */
public class Item {

    private final int index;
    private final double weight;
    private final int price;

    /**
     * Constructor with parameters.
     *
     * @param index  item index
     * @param weight item weight
     * @param price  item price
     */
    public Item(int index, double weight, int price) {
        this.index = index;
        this.weight = weight;
        this.price = price;
    }

    /**
     * Returns item index.
     *
     * @return item index
     */
    public int getIndex() {
        return index;
    }

    /**
     * Returns item weight.
     *
     * @return item weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Returns item price
     *
     * @return item price
     */
    public int getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return index == item.index &&
                Double.compare(item.weight, weight) == 0 &&
                price == item.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, weight, price);
    }
}
