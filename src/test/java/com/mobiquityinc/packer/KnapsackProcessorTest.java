package com.mobiquityinc.packer;

import com.mobiquityinc.model.Item;
import com.mobiquityinc.model.Package;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests for {@link KnapsackProcessor} class.
 */
public class KnapsackProcessorTest {

    /**
     * Validates work of {@link KnapsackProcessor#filter()} method. Should filter items in package.
     */
    @Test
    public void shouldProcessPackage() {
        List<Item> itemList = Arrays.asList(new Item(1, 53.38, 45),
                new Item(2, 88.62, 98),
                new Item(3, 78.48, 3)
        );
        Package packageForProcessing = new Package(81, itemList);
        Package expectedPackage = new Package(81,
                Collections.singletonList(new Item(1, 53.38, 45)));
        Package actualPackage = new KnapsackProcessor(packageForProcessing).filter();
        Assert.assertEquals(expectedPackage, actualPackage);
    }

    /**
     * Validates work of filter method. Should return empty item collection.
     */
    @Test
    public void shouldReturnEmptyItemCollection() {
        Package packageForProcessing = new Package(81, Collections.emptyList());
        Package expectedPackage = new Package(81, Collections.emptyList());
        Package actualPackage = new KnapsackProcessor(packageForProcessing).filter();
        Assert.assertEquals(expectedPackage, actualPackage);
    }
}
