package com.game;

import com.models.*;
import com.models.enums.Dice;
import com.models.enums.ItemType;

import java.util.Scanner;

public class Games {

    public static void startGame(Scanner sc) {
        System.out.println("\n\nBienvenue dans Heroes VERSUS Monster\n\n\n ");
        Hero perso = createPerso(sc);
        loopGame(sc, perso);
    }

    public static Hero createPerso(Scanner sc){
        int userClass = Utils.askClass(sc);
        String userName = Utils.askName(sc);
        Hero perso = null;
        switch (userClass){
            case 1:
                perso = new Human(userName);
                System.out.printf("Défense : %d \nStrength : %d \nHp : %d\n",perso.getDefense(), perso.getStrength(), perso.getHp());
                return perso;
            case 2:
                perso = new Dwarf(userName);
                System.out.printf("Défense : %d \nStrength : %d \nHp : %d\n",perso.getDefense(), perso.getStrength(), perso.getHp());
                return perso;
            default:
                return perso;
        }
    }

    public static void loopGame(Scanner sc, Hero perso){
        Monster enemi = new Orc();
        System.out.println("Vous rentrez dans un combat contre " + enemi.getName() + " !\n");
        while(true){
            int userChoice = Utils.askAction(sc);
            switch(userChoice){
                case 1:
                    int damage = perso.attack();
                    enemi.getDamage(damage);
                    System.out.printf("\nVous infligez %d dégats à %s", damage, enemi.getName());
                    System.out.printf("\nIl lui reste %d points de vie\n", enemi.getHp() );
                    break;
                case 2 :
                    System.out.println("Vous fuyez le combat LOPETTE ! ");
                    return;
                default:
                    break;
            }
            if(enemi.isDead()){
                System.out.println("Vous avez gagné le combat BG BG");
                Item loot = enemi.getLoot();
                int nbr = Dice.D6.roll();
                System.out.println(perso.addXp(enemi.getXpGiven()));
                if(loot.getName() == ItemType.GOLD){
                    perso.addGold(nbr);
                }else{
                    perso.addItem(loot);
                }
                System.out.printf("\nVous looté %d %s", nbr, loot.getName());
                return;
            }
            int enemiDamage = enemi.attack();
            perso.getDamage(enemiDamage);
            System.out.printf("\n%s vous a infligé %d dégats\nIl vous en reste %d\n", enemi.getName(), enemiDamage, perso.getHp());
            if(perso.isDead()){
                System.out.println("Vous avez perdu le combat NOOBY!");
                return;
            }
        }
    }
}
