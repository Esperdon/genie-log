package com.gildedrose;

import static org.junit.Assert.*;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void Sulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 0, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    public void Backstage() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 15, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(11, app.items[0].quality);
    }

    @Test
    public void BackstageSellinInf11() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 9, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(12, app.items[0].quality);
    }

    @Test
    public void BackstageSellinInf6() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 5, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(13, app.items[0].quality);
    }

    @Test
    public void QualiteDepasse50() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50),
                                    new Item("Aged Brie", 2, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        for (int i = 0; i < items.length; i++) {
            assertTrue(app.items[i].quality < 51);
        }
    }

    @Test
    public void AgedBrie() {
        Item[] items = new Item[] { new Item("Aged Brie", -1, 48) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(49, app.items[0].quality);
    }

    @Test
    public void SellInInfDe0() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", -1, 20),
                                    new Item("Elixir of the Mongoose", -1, 20) };

        GildedRose app = new GildedRose(items);
        app.updateQuality();
        for (int i = 0; i < items.length; i++) {
            assertEquals(18, app.items[i].quality);
        }
    }

    @Test
    public void Conjured() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 4, 6),
                                    new Item("Conjured Mana Cake", -1, 6)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        for (int i = 0; i < items.length; i++) {
            if (items[i].sellIn < 0) {
                assertEquals(2, app.items[i].quality);
            }
            else {
                assertEquals(4, app.items[i].quality);
            }
        }
    }
    @Test
    public void QualiteNonNegative() {
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 5, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
    }

    @Test
    public void SellIn() {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Aged Brie", 10, 0),
                new Item("Elixir of the Mongoose", 10, 7),
                new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20),
                new Item("Conjured Mana Cake", 10, 6) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        for (int i = 0; i < items.length; i++) {
            assertEquals(9, app.items[0].sellIn);
        }
    }

    @Test
    public void QualiteDeProduitsNormal() {
        Item[] items = new Item[] {
                new Item("+5 Dexterity Vest", 10, 20),
                new Item("Elixir of the Mongoose", 5, 20) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        for (int i = 0; i < items.length; i++) {
            assertEquals(19, app.items[0].quality);
        }
    }
}
