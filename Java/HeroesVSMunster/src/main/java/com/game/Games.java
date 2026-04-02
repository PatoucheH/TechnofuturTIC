package com.game;

import com.file.FileManager;
import com.models.*;
import com.models.enums.Dice;
import com.models.enums.ItemType;

import java.util.List;
import java.util.Scanner;

import static com.file.FileManager.deleteSave;
import static com.file.FileManager.getPerso;
import static com.game.Utils.*;

public class Games {

    public static void startGame(Scanner sc) {
        System.out.println("==============================================");
        System.out.println("Bienvenue dans Heroes VERSUS Monster\n\n\n");
        int userChoice = askSaveOrNew(sc);
        Hero perso;
        List<String> listSave = FileManager.getFilesName();
        if(userChoice == 1){
            if (listSave == null || listSave.isEmpty()) {
                System.out.println("Aucune sauvegarde, veuillez créer un personnage");
                perso = createPerso(sc);
            } else {
                int choiceSave = selectUserSave(sc, listSave);
                perso = getPerso(listSave.get(choiceSave));
            }
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

    public static void loopFight(Scanner sc, Hero perso, Monster enemy){
        System.out.println("Vous rentrez dans un combat contre " + enemy.getName() +
                "\nQui possède " + Math.max(enemy.getActualHp(), 0) + " points de vie\n");
        while(true){
            int userChoice = askAction(sc);
            switch(userChoice){
                case 1:
                    int damage = perso.attack();
                    enemy.getDamage(damage);
                    System.out.printf("\nVous infligez %d dégats à %s", damage, enemy.getName());
                    System.out.printf("\nIl lui reste %d points de vie sur %d\n",
                            (Math.max(enemy.getActualHp(), 0)), enemy.getMaxHp());
                    break;
                case 2:
                    perso.usePotion();
                    break;
                case 3 :
                    System.out.println("Vous fuyez le combat LOPETTE ! ");
                    return;
                default:
                    break;
            }
            if(enemy.isDead()){
                System.out.println("Vous avez gagné le combat BG BG");
                List<ItemType> loot = enemy.getLoot();
                int nbr = 0;
                System.out.println(perso.addXp(enemy.getXpGiven()));
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
            int enemyDamage = enemy.attack();
            perso.getDamage(enemyDamage);
            System.out.printf("\n%s vous a infligé %d dégats\nIl vous en reste %d / %d\n",
                    enemy.getName(), enemyDamage, Math.max(perso.getActualHp(), 0), perso.getMaxHp());
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
            if(input == 'i'){
                perso.openEquipment();
                selectActionInInventory(sc, perso);
            }
            Monster monster = board.movePlayer(input);
            if (monster != null) {
                System.out.println("Un " + monster.getName() + " apparaît !");
                loopFight(sc, perso, monster);
            }
            if(perso.getPosition().equals(board.getPositionSeller())) {
                System.out.println("Vous entrez chez le marchand");
                board.getSeller().sellItem(sc, perso);
            }
            if (perso.isDead()) {
                System.out.println("Game Over !");
                System.out.println("Relancer le logiciel pour créer une nouvelle partie");
                deleteSave(perso.getName());
                return;
            }
        }
    }
}
