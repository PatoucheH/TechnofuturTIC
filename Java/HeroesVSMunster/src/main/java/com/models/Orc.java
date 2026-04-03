package com.models;

import com.models.enums.ItemType;

import java.util.ArrayList;
import java.util.List;

import static com.models.enums.Dice.*;

public class Orc extends Monster{

    public Orc() {}
    public Orc(Boolean isBoss, int persoLevel){
        int endurance = setStat();
        int hp;
        List<ItemType> lootToAdd = new ArrayList<>();
        lootToAdd.add(ItemType.GOLD);
        if(endurance < 5) hp = endurance -1;
        else if(endurance < 10) hp = endurance ;
        else if(endurance < 15) hp = endurance + 1;
        else hp = endurance + 2;
        super("moyenement méchant orc", hp, endurance, setStat(), lootToAdd, D10.roll(), isBoss, persoLevel);
    }
}
