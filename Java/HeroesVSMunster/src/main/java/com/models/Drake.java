package com.models;

import com.models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

import static com.models.enums.Dice.*;

public class Drake extends Monster{

    public Drake(){
        int endurance = setStat();
        int hp;
        List<ItemType> lootToAdd = new ArrayList<>();
        lootToAdd.add(ItemType.GOLD);
        lootToAdd.add(ItemType.LEATHER);
        if(endurance < 5) hp = endurance -1;
        else if(endurance < 10) hp = endurance ;
        else if(endurance < 15) hp = endurance + 1;
        else hp = endurance + 2;
        super("Grand Dragon badass", hp, endurance, setStat(), lootToAdd, D10.roll());
    }
}
