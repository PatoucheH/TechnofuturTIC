package com.models;

import static com.models.enums.Dice.*;

public class Dwarf extends Hero {
    public Dwarf(){}
    public Dwarf(String name){
        super(name, D10.roll() * 2, 120, D6.roll() * 2, D6.roll() );
    }
}
