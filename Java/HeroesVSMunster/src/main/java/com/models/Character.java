package com.models;

import com.interfaces.Fighter;
import com.models.enums.Dice;

public abstract class Character implements Fighter {
    private String name;
    private int hp;
    private int defense;
    private int strength;

    public Character(){}
    public Character(String name, int hp, int defense, int strength) {
        this.name = name;
        setHp(hp);
        setDefense(defense);
        setStrength(strength);
    }

    public String getName() {
        return name;
    }
    public int getHp() {
        return hp;
    }
    public int getDefense() {
        return defense;
    }
    public int getStrength() {
        return strength;
    }

    protected void setHp(int hp) {
        if(hp <= 0){
            hp = 0;
        }else{
            this.hp = hp;
        }
    }
    protected void setDefense(int defense) {
        if(defense <= 0){
            defense = 0;
        }else{
            this.defense = defense;
        }
    }
    protected void setStrength(int strength) {
        if(strength <= 0){
            strength = 0;
        }else{
            this.strength = strength;
        }
    }

    public boolean isDead(){
        return getHp() <= 0;
    }

    public void getDamage(int damage){
        if(damage < 0) throw new IllegalArgumentException("Les dommages doivent être positifs");
        this.hp -= damage;
    }

    public int attack(){
        return Dice.D6.roll() * getStrength();
    }

}
