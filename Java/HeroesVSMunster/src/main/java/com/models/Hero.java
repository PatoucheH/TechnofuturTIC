package com.models;

import com.models.enums.Dice;

import java.util.*;

public abstract class Hero extends Charactr {
    private int xp;
    private int level;
    private int gold;
    private HashMap<Item, Integer> items;

    public Hero() {}
    public Hero(String name, int gold, int hp, int endurance, int strength) {
        super(name, hp, endurance, strength );
        setGold(gold);
        this.xp = 0;
        this.level = 1;
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

    public void addItem(Item item, int nbr) {
        for (int i = 0; i < nbr; i++) {
            this.items.put(item, this.items.getOrDefault(item, 0) + 1);
        }
    }

    public void addGold(int gold) {
        if(gold > 0) {
            this.gold += gold;
        }
    }

    public void gainStat(){
        int hpWin = Dice.D20.roll();
        int strengthWin = Dice.D4.roll();
        int enduranceWin = Dice.D4.roll();
        this.setMaxHp(this.getMaxHp() + hpWin);
        this.setStrength(this.getStrength() + strengthWin);
        this.setEndurance(this.getEndurance() + enduranceWin);
        System.out.printf("\nVous avez gagné %d hp, %d de force et %d d'endurance", hpWin, strengthWin, enduranceWin);
    }

    private void addLevel(){
        System.out.println("Vous êtes monté au niveau : " +  ++this.level);
        gainStat();
        setActualHp(getMaxHp());
    }

    public String addXp(int xp) {
        String returnValue =  "";
        this.xp += Math.max(xp, 0);
        returnValue = "\nVous avez gagné : " + xp + " d'xp";
        if(this.xp > getLevel() * 10) {
            addLevel();
            this.xp -= getLevel() * 10;
        }
        return returnValue;
    }
}
