package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    private Item update(Item item) {
        GildedRose app = new GildedRose(new Item[]{item});
        app.updateQuality();
        return app.items[0];
    }

    @Test
    void normalItem_decreasesQuality() {
        Item item = update(new Item("foo", 10, 20));

        assertEquals("foo, 9, 19", item.toString());
    }

    @Test
    void normalItem_Negative() {
        Item item = update(new Item("foo", 10, 0));

        assertEquals("foo, 9, 0", item.toString());
    }

    @Test
    void normalItem_expired() {
        Item item = update(new Item("foo", 0, 10));

        assertEquals("foo, -1, 8", item.toString());
    }

    @Test
    void agedBrie_increasesQuality() {
        Item item = update(new Item("Aged Brie", 10, 10));

        assertEquals("Aged Brie, 9, 11", item.toString());
    }

    @Test
    void agedBrie_expired() {
        Item item = update(new Item("Aged Brie", 0, 10));

        assertEquals("Aged Brie, -1, 12", item.toString());
    }

    @Test
    void agedBrie_maxQuality50() {
        Item item = update(new Item("Aged Brie", 5, 50));

        assertEquals("Aged Brie, 4, 50", item.toString());
    }

    @Test
    void backstage_moreThan10() {
        Item item = update(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));

        assertEquals("Backstage passes to a TAFKAL80ETC concert, 14, 21", item.toString());
    }

    @Test
    void backstage_lessThan10() {
        Item item = update(new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20));

        assertEquals("Backstage passes to a TAFKAL80ETC concert, 9, 22", item.toString());
    }

    @Test
    void backstage_lessThan5() {
        Item item = update(new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20));

        assertEquals("Backstage passes to a TAFKAL80ETC concert, 4, 23", item.toString());
    }

    @Test
    void backstage_expired() {
        Item item = update(new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20));

        assertEquals("Backstage passes to a TAFKAL80ETC concert, -1, 0", item.toString());
    }

    @Test
    void sulfuras_neverChanges() {
        Item item = update(new Item("Sulfuras, Hand of Ragnaros", 0, 80));

        assertEquals("Sulfuras, Hand of Ragnaros, 0, 80", item.toString());
    }
}