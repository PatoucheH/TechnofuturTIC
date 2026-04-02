package com.models;

import static com.models.enums.Dice.*;

public class Human extends Hero {

    public Human(){}
    public Human(String name) {
        int endurance = setStat();
        int hp;
        if(endurance < 5) hp = endurance -1;
        else if(endurance < 10) hp = endurance ;
        else if(endurance < 15) hp = endurance + 1;
        else hp = endurance + 2;
        super(name, D10.roll(), hp , endurance + 2, setStat() + 1);
    }
}
