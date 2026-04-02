package com.models;

import static com.models.enums.Dice.*;

public class Dwarf extends Hero {

    public Dwarf(){}
    public Dwarf(String name){
        int endurance = setStat();
        int hp;
        if(endurance < 5) hp = endurance -1;
        else if(endurance < 10) hp = endurance ;
        else if(endurance < 15) hp = endurance + 1;
        else hp = endurance + 2;
        super(name, D10.roll() * 2, hp, endurance , setStat() + 2);
    }
}
