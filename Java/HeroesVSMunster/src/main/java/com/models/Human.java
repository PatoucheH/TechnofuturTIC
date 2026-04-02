package com.models;

import static com.models.enums.Dice.*;

public class Human extends Hero {

    public Human(){}
    public Human(String name) {
        super(name, D10.roll() , setStat() + 4, setStat() + 1);
    }
}
