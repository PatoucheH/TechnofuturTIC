package com.models;

import static com.models.enums.Dice.*;

public class Human extends Hero {

    public Human(){}
    public Human(String name) {
        super(name, D10.roll(), 100, D6.roll(), D6.roll() * 2);
    }
}
