package com.example.orirecipe;

public class FoodData {

    private String itemName, itemDesc, itemImage;

    public FoodData(String itemName, String itemDesc, String itemImage) {
        this.itemName = itemName;
        this.itemDesc = itemDesc;
        this.itemImage = itemImage;
    }

    public FoodData (){}

    public String getItemName() {
        return itemName;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public String getItemImage() {
        return itemImage;
    }
}

