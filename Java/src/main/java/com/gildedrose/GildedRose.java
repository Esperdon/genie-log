package com.gildedrose;

class GildedRose {
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    private int Qualite_Produit (int i) {
        return items[i].quality;
    }

    private int SellIn_Produit (int i) {
        return items[i].sellIn;
    }

    private String Nom_Produit(int i) {
        return items[i].name;
    }

    private void Qualite_Diminue_1 (int i) {
        items[i].quality = items[i].quality - 1;
    }

    private void Qualite_Augment_1 (int i) {
        items[i].quality = items[i].quality + 1;
    }

    private void SellIn_Diminue_1 (int i) {
        items[i].sellIn = items[i].sellIn - 1;
    }

    private void Conjured (int i) {
        if (Nom_Produit(i).equals("Conjured Mana Cake")) {
            Qualite_Diminue_1(i);
        }
    }

    private void Qualite_Produits_Sans_Sulfuras (int i) {
        if (!Nom_Produit(i).equals("Sulfuras, Hand of Ragnaros")) {
            Qualite_Diminue_1(i);
        }
    }

    private void SellIn_Produits_Sans_Sulfuras (int i) {
        if (!Nom_Produit(i).equals("Sulfuras, Hand of Ragnaros")) {
            SellIn_Diminue_1(i);
        }
    }

    private void Qualite_Inferieur_A50 (int i) {
        if (Qualite_Produit(i) < 50) {
            Qualite_Augment_1(i);
        }
    }

    private void Qualite_Sans_AgedBrid_Et_Backstage (int i) {
        if (Qualite_Produit(i) > 0) {
            Qualite_Produits_Sans_Sulfuras(i);
            Conjured(i);
        }
    }

    private void Backstage (int i) {
        Qualite_Inferieur_A50(i);

        if (SellIn_Produit(i) < 11) { Qualite_Inferieur_A50(i); }

        if (SellIn_Produit(i) < 6) { Qualite_Inferieur_A50(i); }

        if (SellIn_Produit(i) < 0) { items[i].quality = items[i].quality - items[i].quality; }

    }

    private void AgidBrie (int i) {
        if (Qualite_Produit(i) < 50 || SellIn_Produit(i) < 0) {
            Qualite_Augment_1(i);
        }
    }

    private void Tous_Produits_Sans_AgedBrid_Et_Backstage (int i) {
        if (!Nom_Produit(i).equals("Aged Brie") && !Nom_Produit(i).equals("Backstage passes to a TAFKAL80ETC concert")) {
            Qualite_Sans_AgedBrid_Et_Backstage(i);

            if (SellIn_Produit(i) < 0) {
                Qualite_Sans_AgedBrid_Et_Backstage(i);
            }
        }
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {

            Tous_Produits_Sans_AgedBrid_Et_Backstage(i);

            SellIn_Produits_Sans_Sulfuras(i);
            
            if (Nom_Produit(i).equals("Aged Brie")) { AgidBrie(i);}

            if (Nom_Produit(i).equals("Backstage passes to a TAFKAL80ETC concert")) { Backstage(i); }
        }
    }
}