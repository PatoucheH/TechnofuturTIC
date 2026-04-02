package com.game;

import com.file.FileManager;
import com.models.*;
import com.models.enums.Dice;
import com.models.enums.ItemType;

import java.util.List;
import java.util.Scanner;

import static com.file.FileManager.getPerso;
import static com.game.Utils.*;

public class Games {

    public static void startGame(Scanner sc) {
        System.out.println("==============================================");
        System.out.println("Bienvenue dans Heroes VERSUS Monster\n\n\n");
        int userChoice = askSaveOrNew(sc);
        Hero perso;
        if(userChoice == 1){
            List<String> listSave = FileManager.getFilesName();
            int choiceSave = selectUserSave(sc, listSave);
            perso = getPerso(listSave.get(choiceSave));
        }else{
            perso = createPerso(sc);
        }
        Board board = new Board(10, 10, 15, perso);
        gameLoop(sc, perso, board);
    }

    public static Hero createPerso(Scanner sc){
        int userClass = askClass(sc);
        String userName = askName(sc);
        Hero perso = null;
        switch (userClass){
            case 1:
                perso = new Human(userName);
                return perso;
            case 2:
                perso = new Dwarf(userName);
                return perso;
            default:
                return perso;
        }
    }

    public static void loopFight(Scanner sc, Hero perso, Monster enemi){
        System.out.println("Vous rentrez dans un combat contre " + enemi.getName() +
                "\nQui possède " + enemi.getActualHp() + " points de vie\n");
        while(true){
            int userChoice = askAction(sc);
            switch(userChoice){
                case 1:
                    int damage = perso.attack();
                    enemi.getDamage(damage);
                    System.out.printf("\nVous infligez %d dégats à %s", damage, enemi.getName());
                    System.out.printf("\nIl lui reste %d points de vie sur %d\n",
                            (Math.max(enemi.getActualHp(), 0)), enemi.getMaxHp());
                    break;
                case 2 :
                    System.out.println("Vous fuyez le combat LOPETTE ! ");
                    return;
                default:
                    break;
            }
            if(enemi.isDead()){
                System.out.println("Vous avez gagné le combat BG BG");
                List<ItemType> loot = enemi.getLoot();
                int nbr = 0;
                System.out.println(perso.addXp(enemi.getXpGiven()));
                for (ItemType itemType : loot) {
                    if (itemType == ItemType.GOLD) {
                        nbr = Dice.D6.roll();
                        perso.addGold(nbr);
                    } else {
                        nbr = Dice.D4.roll();
                        perso.addItem(itemType, nbr);
                    }
                    System.out.printf("\nVous looté %d %s\n", nbr, itemType);
                }
                break;
            }
            int enemiDamage = enemi.attack();
            perso.getDamage(enemiDamage);
            System.out.printf("\n%s vous a infligé %d dégats\nIl vous en reste %d / %d\n",
                    enemi.getName(), enemiDamage, perso.getActualHp(), perso.getMaxHp());
            if(perso.isDead()){
                System.out.println("Vous avez perdu le combat NOOBY!");
                return;
            }
        }
        System.out.println("Appuyer sur une touche pour continuer....");
        sc.nextLine();
    }

    public static void gameLoop(Scanner sc, Hero perso, Board board) {
        while (true) {
            System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            board.display();
            displayPersoData(perso);
            char input = askMove(sc);
            if(input == 'e'){
                save(perso);
                return;
            }
            Monster monster = board.movePlayer(input);
            if (monster != null) {
                System.out.println("Un " + monster.getName() + " apparaît !");
                loopFight(sc, perso, monster);
            }
            if (perso.isDead()) {
                System.out.println("Game Over !");
                System.out.println("Relancer le logiciel pour créer une nouvelle partie");
                return;
            }
        }
    }
}
