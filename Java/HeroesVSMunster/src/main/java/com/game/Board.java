package com.game;

import com.models.Hero;
import com.models.Monster;

import java.util.Random;

public class Board {
    private int width;
    private int height;
    private int playerX;
    private int playerY;

    private Monster[][] monsters;

    public Board(int width, int height, int nbrMonsters) {
        this.width = width;
        this.height = height;
        monsters = new Monster[height][width];
        playerX = width / 2;
        playerY = height / 2;
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
        System.out.println("============= MAP =============");
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == playerY && j == playerX) {
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
        System.out.println("===============================");
    }

    public Monster movePlayer(char input) {
        int newX = playerX;
        int newY = playerY;
        switch (input) {
            case 'z': newY--; break;
            case 's': newY++; break;
            case 'q': newX--; break;
            case 'd': newX++; break;
        }
        if (newX < 0 || newX >= width || newY < 0 || newY >= height) {
            return null;
        }
        playerX = newX;
        playerY = newY;
        Monster monster = monsters[playerY][playerX];
        if (monster != null) {
            monsters[playerY][playerX] = null;
            return monster;
        }
        return null;
    }
}