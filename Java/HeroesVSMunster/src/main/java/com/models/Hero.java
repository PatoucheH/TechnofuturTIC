package com.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.models.enums.Dice;
import com.models.enums.ItemType;

import java.util.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.WRAPPER_OBJECT
)
public abstract class Hero extends Charactr {
    private int xp;
    private int level;
    private int gold;
    private Position position;
    private HashMap<ItemType, Integer> items;

    public Hero() {}
    public Hero(String name, int gold, int endurance, int strength) {
        int hp;
        if(endurance < 5) hp = endurance -1;
        else if(endurance < 10) hp = endurance;
        else if(endurance < 15) hp = endurance + 1;
        else hp = endurance + 2;
        super(name, hp, endurance, strength );
        setGold(gold);
        setXp(0);
        setLevel(1);
        setItems(new HashMap<>());
        setPosition( new Position(0, 0));
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
    public HashMap<ItemType, Integer> getItems() {
        return items;
    }
    public Position getPosition() {
        return position;
    }

    public void setGold(int gold) {
        this.gold = Math.max(gold, 0);
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setXp(int xp){
        this.xp = xp;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public void setItems(HashMap<ItemType, Integer> items){
        this.items = items;
    }

    public void addItem(ItemType item, int nbr) {
        if (nbr <= 0) return;
        this.items.put(item, this.items.getOrDefault(item, 0) + nbr);
    }

    public void addGold(int gold) {
        if(gold > 0) {
            this.gold += gold;
        }
    }
    public int removeGold(int gold){
        if(gold > 0){
            this.gold -= gold;
            return getGold();
        }else{
            System.out.println("Doit retirer un nombre positif de gold ");
            return 0;
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
            this.xp -= getLevel() * 10;
            addLevel();
        }
        return returnValue;
    }

    public void usePotion() {
        if (this.items.containsKey(ItemType.POTION)) {
            int quantity = this.items.get(ItemType.POTION);
            if (quantity > 1) {
                this.items.put(ItemType.POTION, quantity - 1);
            } else {
                this.items.remove(ItemType.POTION);
            }
            int heal = Dice.D10.roll();
            setActualHp(Math.min(getMaxHp(), getActualHp() + heal ));
            System.out.printf("Vous avez soigné %d et avez actuellement %d points de vie\n", heal, getActualHp());
        }
    }
}
