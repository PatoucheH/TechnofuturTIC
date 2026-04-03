package com.game;

import com.models.*;
import com.models.enums.ItemType;

import java.util.List;
import java.util.Random;

import static com.models.enums.ItemType.*;

public class Board {
    private int width;
    private int height;
    private Hero perso;

    private Monster[][] monsters;
    private Seller seller;

    public Board(int width, int height, int nbrMonsters, Hero perso) {
        this.width = width;
        this.height = height;
        monsters = new Monster[height][width];
        this.perso = perso;
        generateMonsters(nbrMonsters);
        generateSeller();
        generateBoss();
    }

    public Position getPositionSeller(){
        return seller.getPosition();
    }

    public Seller getSeller(){
        return seller;
    }

    private void generateMonsters(int nbrMonsters) {
        Random rand = new Random();
        for (int i = 0; i < nbrMonsters; i++) {
            int x, y;
            do {
                x = rand.nextInt(width);
                y = rand.nextInt(height);
            } while (monsters[y][x] != null || (perso.getPosition().x() == x && perso.getPosition().y() == y));
            monsters[y][x] = Utils.randomMonster(false, perso.getLevel());
        }
    }

    private void generateBoss(){
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width - 3,width);
            y = rand.nextInt(height - 3, height);
        }while(monsters[y][x] != null ||
                (perso.getPosition().x() == x && perso.getPosition().y() == y) ||
                (getPositionSeller().x() == x && getPositionSeller().y() == y));
        monsters[y][x] = Utils.randomMonster(true, perso.getLevel());
    }

    private void generateSeller(){
        List<ItemType> items = List.of(POTION, SWORD, ARMOR, HELMET);
        Random rand = new Random();
        int x, y;
        do {
            x = rand.nextInt(width);
            y = rand.nextInt(height);
        } while (monsters[y][x] != null ||(perso.getPosition().x() == x && perso.getPosition().y() == y));
        seller = new Seller(items);
        seller.setPosition(new Position(x, y));
    }

    public void display() {
        System.out.println("=================== MAP ===================");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                String result = "";
                if (i == perso.getPosition().y() && j == perso.getPosition().x()) {
                    result = " 🧙 ";
                } else if (monsters[i][j] != null && monsters[i][j] instanceof Orc) {
                    if(monsters[i][j].isBoss()){
                        result = " 🧌❗";
                    }else{
                        result = " 🧌 ";
                    }
                } else if (monsters[i][j] != null && monsters[i][j] instanceof Drake) {
                    if(monsters[i][j].isBoss()){
                        result = " 🐉❗";
                    }else{
                        result = " 🐉 ";
                    }
                }else if (monsters[i][j] != null && monsters[i][j] instanceof Gobelins) {
                    if(monsters[i][j].isBoss()){
                        result = " 👹❗";
                    }else{
                        result = " 👹 ";
                    }
                }else if(i == seller.getPosition().y() && j == seller.getPosition().x()) {
                    result = " 🛒 " ;
                }else {
                    result =  " ⬛ ";
                }

                System.out.print(result);
            }
            System.out.println();
        }
        System.out.println("===========================================");
    }

    public Monster movePlayer(char input) {
        int newX = perso.getPosition().x();
        int newY = perso.getPosition().y();
        switch (input) {
            case 'z': newY--; break;
            case 's': newY++; break;
            case 'q': newX--; break;
            case 'd': newX++; break;
        }
        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return null;
        }
        perso.setPosition(new Position(newX, newY));
        Monster monster = monsters[perso.getPosition().y()][perso.getPosition().x()];
        if (monster != null) {
            monsters[perso.getPosition().y()][perso.getPosition().x()] = null;
            return monster;
        }
        return null;
    }
}