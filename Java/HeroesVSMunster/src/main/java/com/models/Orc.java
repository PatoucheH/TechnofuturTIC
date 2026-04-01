package com.models;

import com.models.enums.ItemType;

import static com.models.enums.Dice.*;

public class Orc extends Monster{

    public Orc(){
        super("blbl l'orc", D10.roll() * 10, D4.roll(), D4.roll(), new Item(ItemType.GOLD, 1), D10.roll());
    }
}
