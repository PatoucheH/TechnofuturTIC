package com.models;

import com.models.enums.ItemType;

import static com.models.enums.Dice.*;

public class Drake extends Monster{

    public Drake(){
        super("Gros méchant dragon", D20.roll() * 10, D10.roll(), D6.roll(), new Item(ItemType.LEATHER, 3), D20.roll());

    }
}
