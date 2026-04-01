package com.models;

import com.models.enums.Dice;

import java.util.HashMap;

public abstract class Hero extends Character {
    private int xp;
    private int level;
    private int gold;
    private HashMap<Item, Integer> items;

    public Hero() {}
    public Hero(String name, int gold, int hp, int defense, int strength) {
        super(name, hp, defense, strength );
        setGold(gold);
        this.xp = 0;
        this.level = 0;
        this.items = new HashMap<>();
    }

    public int getXp() {
        return xp;
    }
    public int getLevel() {
        return level;
    }
    public int getGold() {
        return gold;
    }
    public HashMap<Item, Integer> getItems() {
        return items;
    }

    public void setGold(int gold) {
        this.gold = Math.max(gold, 0);
    }

    public void addItem(Item item) {
        this.items.put(item, this.items.getOrDefault(item, 0) + 1);
    }

    public void addGold(int gold) {
        if(gold > 0) {
            this.gold += gold;
        }
    }

    public void gainStat(){
        int hpWin = Dice.D20.roll();
        int strengthWin = Dice.D4.roll();
        int defenseWin = Dice.D4.roll();
        this.setHp(this.getHp() + hpWin);
        this.setStrength(this.getStrength() + strengthWin);
        this.setDefense(this.getDefense() + defenseWin);
        System.out.printf("\nVous avez gagné %d hp, %d de force et %d de defense", hpWin, strengthWin, defenseWin);
    }

    private void addLevel(){
        this.level++;
        gainStat();
    }

    public String addXp(int xp) {
        String returnValue =  "";
        this.xp += Math.max(xp, 0);
        returnValue = "Vous avez gagné : " +  getXp() + "d'xp";
        if(this.xp > getLevel() * 100) {
            addLevel();
            this.xp -= getLevel() * 100;
            returnValue = "Vous êtes monté au niveau : " + getLevel();
        }
        return returnValue;
    }
}
