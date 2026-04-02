package com.models;

import static com.models.enums.Dice.*;

public class Dwarf extends Hero {

    public Dwarf(){}
    public Dwarf(String name){
        super(name, D10.roll() * 2, setStat() , setStat() + 3);
    }
}
