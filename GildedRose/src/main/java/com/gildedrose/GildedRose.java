package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void doAgedBrie(int i) {
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;
        }

        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {
            if (items[i].quality < 50) {
                items[i].quality = items[i].quality + 1;
            }
        }
    }

    public void doBackstagePasses(int i) {
        if (items[i].quality < 50) {
            items[i].quality = items[i].quality + 1;

            if (items[i].sellIn < 11) {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }

            if (items[i].sellIn < 6) {
                if (items[i].quality < 50) {
                    items[i].quality = items[i].quality + 1;
                }
            }
        }

        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {
            items[i].quality = 0;
        }
    }

    public void doSulfuras(int i) {
        //rien
    }

    public void doNormalItem(int i) {
        if (items[i].quality > 0) {
            items[i].quality = items[i].quality - 1;
        }
        items[i].sellIn = items[i].sellIn - 1;


        if (items[i].sellIn < 0) {
            if (items[i].quality > 0) {
                items[i].quality = items[i].quality - 1;
            }
        }
    }

    public void doConjuredItem(int i) {
        if (items[i].quality > 0) {
            items[i].quality = items[i].quality - 2;
        }

        items[i].sellIn = items[i].sellIn - 1;

        if (items[i].sellIn < 0) {
            if (items[i].quality > 0) {
                items[i].quality = items[i].quality - 2;
            }
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            switch (items[i].name) {
                case "Aged Brie":
                    doAgedBrie(i);
                    break;
                case "Backstage passes to a TAFKAL80ETC concert":
                    doBackstagePasses(i);
                    break;
                case "Sulfuras, Hand of Ragnaros":
                    doSulfuras(i);
                    break;
                case "Conjured":
                    doConjuredItem(i);
                    break;
                default:
                    doNormalItem(i);
            }
        }
    }
}
