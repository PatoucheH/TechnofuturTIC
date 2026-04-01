package com.models;

import com.models.enums.ItemType;

public class Item {
    private final ItemType name;
    private final int cost;

    public Item(ItemType name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public ItemType getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
}
