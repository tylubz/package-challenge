package com.mobiquityinc.packer;

import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class which is responsible for processing knapsack problem.
 */
public class KnapsackProcessor {

    private final double[][] matrix;

    private final List<Item> itemList;

    private final int weight;

    /**
     * Constructor with parameters.
     *
     * @param pkg package
     */
    public KnapsackProcessor(Package pkg) {
        this.itemList = pkg.getItemList();
        this.weight = pkg.getWeight();
        this.matrix = initMatrix();
    }

    private double[][] initMatrix() {
        double[][] matrix = new double[weight + 1][itemList.size()];
        for (int i = 0; i < weight + 1; i++) {
            for (int j = 0; j < itemList.size(); j++) {
                matrix[i][j] = -1;
            }
        }
        return matrix;
    }

    /**
     * Returns filtered package which satisfies next conditions:
     * 1) Total weight of items is less than maximum package weight.
     * 2) Total cost of items as much as possible.
     *
     * @return filtered package
     */
    public Package filter() {
        fillMatrix(weight, itemList.size() - 1);
        return new Package(weight, getFilteredItems());
    }

    private double fillMatrix(int j, int i) {
        if (i < 0 || j < 0) {
            return 0;
        }
        Item item = itemList.get(i);
        double with, cell = matrix[j][i];
        if (cell == -1) {
            if (item.getWeight() > j) {
                with = -1;
            } else {
                with = item.getPrice() + fillMatrix(j - (int) item.getWeight(), i - 1);
            }
            cell = Math.max(with, fillMatrix(j, i - 1));
            matrix[j][i] = cell;
        }
        return cell;
    }

    private List<Item> getFilteredItems() {
        List<Item> filteredItems = new ArrayList<>();
        int i = itemList.size() - 1;
        int j = weight;
        while (i >= 0) {
            Item item = itemList.get(i);
            double without = i == 0 ? 0 : matrix[j][i - 1];
            if (Double.compare(matrix[j][i], without) != 0) {
                filteredItems.add(item);
                j -= (int) item.getWeight();
            }
            i--;
        }
        filteredItems.sort(Comparator.comparingInt(Item::getIndex));
        return filteredItems;
    }
}