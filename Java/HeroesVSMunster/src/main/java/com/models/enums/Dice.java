package com.models.enums;

import java.util.Random;

public enum Dice {
    D2(2),
    D3(3),
    D4(4),
    D6(6),
    D10(10),
    D20(20),
    D100(100);

    private final int faces;

    Dice(int faces){
        this.faces = faces;
    }

    public int roll(){
        Random rand = new Random();
        return rand.nextInt(faces) + 1;
    }

}
