package com.models;


import com.models.enums.ItemType;

import java.util.List;

public abstract class Monster extends Charactr {
    private List<ItemType> loot;
    private int xpGiven;

    public Monster(){}
    public Monster(String name, int hp, int defense, int strength, List<ItemType> loot, int xpGiven){
        super(name, hp, defense, strength);
        this.loot = loot;
        this.xpGiven = xpGiven;
    }

    public List<ItemType> getLoot(){
        return loot;
    }
    public int  getXpGiven(){
        return xpGiven;
    }

}
