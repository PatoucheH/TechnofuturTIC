package com.game;

import com.models.Hero;
import com.models.Monster;
import com.models.Position;

import java.util.Random;

public class Board {
    private int width;
    private int height;
    private Hero perso;

    private Monster[][] monsters;

    public Board(int width, int height, int nbrMonsters, Hero perso) {
        this.width = width;
        this.height = height;
        monsters = new Monster[height][width];
        this.perso = perso;
        generateMonsters(nbrMonsters);
    }

    private void generateMonsters(int nbrMonsters) {
        Random rand = new Random();
        for (int i = 0; i < nbrMonsters; i++) {
            int x = rand.nextInt(width);
            int y = rand.nextInt(height);
            monsters[y][x] = Utils.randomMonster();
        }
    }

    public void display() {
        System.out.println("=================== MAP ===================");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == perso.getPosition().y() && j == perso.getPosition().x()) {
                    System.out.print(" 🧙 ");
                }
                else if (monsters[i][j] != null) {
                    System.out.print(" 👹 ");
                }
                else {
                    System.out.print(" ⬛ ");
                }
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