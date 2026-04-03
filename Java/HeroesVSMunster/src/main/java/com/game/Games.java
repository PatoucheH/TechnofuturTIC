package com.game;

import com.file.FileManager;
import com.models.*;
import com.models.enums.Dice;
import com.models.enums.ItemType;

import java.sql.SQLOutput;
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
        int width = 10 + 5 * perso.getMapPassed();
        int height = 10 + 5 * perso.getMapPassed();
        int nbrMonster = 15 + 5 * perso.getMapPassed();
        Board board = new Board(width, height, nbrMonster, perso);
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
        System.out.println("Vous rentrez dans un combat contre " + enemy.getName());
        while(true){
            System.out.printf("\n=========== Stats enemi ===========\nHp : %d/%d\nForce : %d   |   Endurance : %d\n",
                    enemy.getActualHp(), enemy.getMaxHp(), enemy.getStrength(), enemy.getEndurance());
            System.out.println("===================================");
            int userChoice = askAction(sc);
            switch(userChoice){
                case 1:
                    int damage = perso.attack();
                    enemy.getDamage(damage);
                    System.out.printf("\nVous infligez %d dégats à %s", damage, enemy.getName());
                    System.out.printf("\nIl lui reste %d points de vie sur %d\n",
                            Math.max(enemy.getActualHp(), 0), enemy.getMaxHp());
                    break;
                case 2:
                    boolean potionTaken = perso.usePotion();
                    if(!potionTaken){
                        System.out.println("Vous n'avez plus de potion ");
                        continue;
                    }
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
            System.out.printf("\n%s vous a infligé %d dégats mais vous vou protégez de %d grâce à votre armure" +
                            "\nIl vous en reste %d / %d\n\n",
                    enemy.getName(), enemyDamage, perso.getArmor(), Math.max(perso.getActualHp(), 0), perso.getMaxHp());
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
            Monster monster = board.movePlayer(sc, input);
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
            if(monster != null && monster.isBoss() && monster.isDead()){
                perso.setMapPassed(perso.getMapPassed() + 1);
                int width = 10 + 8 * perso.getMapPassed();
                int height = 10 + 4 * perso.getMapPassed();
                int nbrMonster = 15 + 7 * perso.getMapPassed();
                System.out.println("Vous avez battu le boss de la map ! Félicitations !\n\nVous allez passer à la map suivante");
                sc.nextLine();
                Board newBoard = new Board(width, height, nbrMonster, perso);
                gameLoop(sc, perso, newBoard);
                return;
            }
        }
    }
}
