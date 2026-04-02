package com.models;


import java.util.List;

public abstract class Monster extends Charactr {
    private List<Item> loot;
    private int xpGiven;

    public Monster(){}
    public Monster(String name, int hp, int defense, int strength, List<Item> loot, int xpGiven){
        super(name, hp, defense, strength);
        this.loot = loot;
        this.xpGiven = xpGiven;
    }

    public List<Item> getLoot(){
        return loot;
    }
    public int  getXpGiven(){
        return xpGiven;
    }

}
