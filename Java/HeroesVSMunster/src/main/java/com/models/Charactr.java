package com.models;

import com.interfaces.Fighter;
import com.models.enums.Dice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class Charactr implements Fighter {
    private String name;
    private int maxHp;
    private int actualHp;
    private int endurance;
    private int strength;

    public Charactr(){}
    public Charactr(String name, int hp, int endurance, int strength) {
        setName(name);
        setMaxHp(hp);;
        setActualHp(hp);
        setEndurance(endurance);
        setStrength(strength);
    }

    public String getName() {
        return name;
    }
    public int getMaxHp() {
        return maxHp;
    }
    public int getActualHp() {
        return actualHp;
    }
    public int getEndurance() {
        return endurance;
    }
    public int getStrength() {
        return strength;
    }

    public void setMaxHp(int maxHp) {
        if(maxHp <= 0){
            maxHp = 0;
        }else{
            this.maxHp = maxHp;
        }
    }
    public void setActualHp(int actualHp) {
        if(actualHp <= 0){
            actualHp = 0;
        }else{
            this.actualHp = actualHp;
        }
    }
    public void setEndurance(int defense) {
        if(defense <= 0){
            defense = 0;
        }else{
            this.endurance = defense;
        }
    }
    public void setStrength(int strength) {
        if(strength <= 0){
            strength = 0;
        }else{
            this.strength = strength;
        }
    }
    public void setName(String name) {
        this.name = name;
    }

    public static int setStat(){
        List<Integer> statDice = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++) {
            statDice.add(Dice.D6.roll());
        }
        Collections.sort(statDice);
        return statDice.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
    }

    public boolean isDead(){
        return getActualHp() <= 0;
    }

    public void getDamage(int damage){
        if(damage < 0) throw new IllegalArgumentException("Les dommages doivent être positifs");
        this.actualHp -= damage;
    }

    public int attack(){
        if(this.strength < 5) return Dice.D4.roll() - 1;
        else if(this.strength < 10) return Dice.D4.roll();
        else if(this.strength < 15) return Dice.D4.roll() + 1;
        else return Dice.D4.roll() + 2;
    }

}
