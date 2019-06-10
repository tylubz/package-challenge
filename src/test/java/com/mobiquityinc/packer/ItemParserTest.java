package com.mobiquityinc.packer;

import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.model.Item;
import org.junit.Assert;
import org.junit.Test;

public class ItemParserTest {

    @Test()
    public void shouldBeParsedProperly() {
        Item expectedItem = new Item(2, 3.72, 10);
        Item actualItem = ItemParser.parse("(2,3.72,€10)");
        Assert.assertEquals(expectedItem, actualItem);
    }

    @Test(expected = APIException.class)
    public void itemPriceIsMoreThanMaximum() {
        ItemParser.parse("(1,90.72,€101)");
    }

    @Test(expected = APIException.class)
    public void itemWeightIsMoreThanMaximum() {
        ItemParser.parse("(1,100.72,€10)");
    }

    @Test(expected = APIException.class)
    public void itemIndexIsNotInteger() {
        ItemParser.parse("(1.2,3.72,€10)");
    }

    @Test(expected = APIException.class)
    public void itemIndexIsNegative() {
        ItemParser.parse("(-1,3.72,€10)");
    }

    @Test(expected = APIException.class)
    public void itemWeightIsNegative() {
        ItemParser.parse("(-1,3.72,€10)");
    }

    @Test(expected = APIException.class)
    public void itemPriceMissingEuroSign() {
        ItemParser.parse("(2,3.72,10)");
    }

    @Test(expected = APIException.class)
    public void itemMissingOpeningBracket() {
        ItemParser.parse("2,3.72,€10)");
    }

    @Test(expected = APIException.class)
    public void itemMissingClosingBracket() {
        ItemParser.parse("2,3.72,€10");
    }
}