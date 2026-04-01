package com.models;


public abstract class Monster extends Character {
    private Item loot;
    private int xpGiven;

    public Monster(){}
    public Monster(String name, int hp, int defense, int strength, Item loot, int xpGiven){
        super(name, hp, defense, strength);
        this.loot = loot;
        this.xpGiven = xpGiven;
    }

    public Item getLoot(){
        return loot;
    }
    public int  getXpGiven(){
        return xpGiven;
    }

}
