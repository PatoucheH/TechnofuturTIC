package com.models;


import com.models.enums.ItemType;

import java.util.List;

import static com.models.enums.Dice.*;

public abstract class Monster extends Charactr {
    private List<ItemType> loot;
    private int xpGiven;
    private boolean isBoss;

    public Monster(){}
    public Monster(String name, int hp, int defense, int strength, List<ItemType> loot, int xpGiven, boolean isBoss, int persoLevel){
        this.isBoss = isBoss;
        this.loot = loot;
        this.xpGiven = xpGiven;
        if(isBoss){
            hp *= 2;
            defense *= 2;
            strength *= 2;
        }
        super(name, hp, defense, strength);
        calculateBonus(persoLevel);
    }

    public List<ItemType> getLoot(){
        return loot;
    }
    public int  getXpGiven(){
        return xpGiven;
    }
    public boolean isBoss(){
        return isBoss;
    }

    public void calculateBonus(int persoLevel){
        setMaxHp(getMaxHp() + (D6.roll() * persoLevel - 1));
        setActualHp(getMaxHp());
        setEndurance(getEndurance() + (D4.roll() * persoLevel - 1));
        setStrength(getStrength() + (D4.roll() * persoLevel - 1));
    }

}
