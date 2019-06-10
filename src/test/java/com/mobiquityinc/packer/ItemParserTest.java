package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests for {@link ItemParser} class.
 */
public class ItemParserTest {

    /**
     * Validates work of {@link ItemParser#parse(String)} method.
     * Should process string to {@link Item} class.
     */
    @Test()
    public void shouldBeParsedProperly() {
        Item expectedItem = new Item(2, 3.72, 10);
        Item actualItem = ItemParser.parse("(2,3.72,€10)");
        Assert.assertEquals(expectedItem, actualItem);
    }

    /**
     * Test for validating max item price.
     */
    @Test(expected = APIException.class)
    public void itemPriceIsMoreThanMaximum() {
        ItemParser.parse("(1,90.72,€101)");
    }

    /**
     * Test for validating max item weight.
     */
    @Test(expected = APIException.class)
    public void itemWeightIsMoreThanMaximum() {
        ItemParser.parse("(1,100.72,€10)");
    }

    /**
     * Test for validating item index format.
     */
    @Test(expected = APIException.class)
    public void itemIndexIsNotInteger() {
        ItemParser.parse("(1.2,3.72,€10)");
    }

    /**
     * Test for validating item index format. Should not contain negative numbers.
     */
    @Test(expected = APIException.class)
    public void itemIndexIsNegative() {
        ItemParser.parse("(-1,3.72,€10)");
    }

    /**
     * Test for validating item weight format.
     */
    @Test(expected = APIException.class)
    public void itemWeightIsNegative() {
        ItemParser.parse("(1,-3.72,€10)");
    }

    /**
     * Test for validating price format. Should contain euro (€) sign.
     */
    @Test(expected = APIException.class)
    public void itemPriceMissingEuroSign() {
        ItemParser.parse("(2,3.72,10)");
    }

    /**
     * Test for validating item structure. Should contain open bracket.
     */
    @Test(expected = APIException.class)
    public void itemMissingOpeningBracket() {
        ItemParser.parse("2,3.72,€10)");
    }

    /**
     * Test for validating item structure. Should contain closing bracket.
     */
    @Test(expected = APIException.class)
    public void itemMissingClosingBracket() {
        ItemParser.parse("2,3.72,€10");
    }
}