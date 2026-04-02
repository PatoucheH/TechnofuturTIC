package com.game;

import com.models.*;

import java.util.Map;
import java.util.Scanner;

public class Utils {

    public static String askName(Scanner scanner){
        while (true){
            System.out.print("Entrez le nom de votre personnage : ");
            String userName = scanner.nextLine();
            if(userName.isEmpty()){
                System.out.println("Erreur : Le nom ne peut pas être vide");
            }else if(userName.length() < 3){
                System.out.println("Erreur : Votre personnage doit avoir un nom de 3 caractères minimum");
            }else{
                return userName;
            }
        }
    }

    public static int askClass(Scanner scanner){
        while (true){
            System.out.println("Quel classe voulez-vous jouer ?\n1. Humain\n2. Nain");
            String userChoice = scanner.nextLine();
            if(!userChoice.equals("1") && !userChoice.equals("2")){
                System.out.println("Erreur : Entrez un numéro valide");
            }else{
                return Integer.parseInt(userChoice);
            }
        }
    }

    public static int askAction(Scanner scanner){
        int userNombre = 0;
        while (true){
            System.out.println("Que voulez-vous faire ?\n1. Attaquer l'enemi\n2. Fuir");
            String userChoice = scanner.nextLine();
            try {
                userNombre = Integer.parseInt(userChoice);

                if (userNombre >= 1 && userNombre <= 3) {
                    return userNombre;
                } else {
                    System.out.println("Erreur : Le nombre doit être compris entre 1 et 3.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Ce n'est pas un nombre entier valide.");
            }
        }
    }

    public static char askMove(Scanner sc) {
        while (true) {
            System.out.print("ZQSD pour bouger : ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.length() == 1) {
                char c = input.charAt(0);
                if (c == 'z' || c == 'q' || c == 's' || c == 'd') {
                    return c;
                }
            }
            System.out.println("Entrée invalide ! Utilise uniquement Z Q S D");
        }
    }

    public static Monster randomMonster() {
        int rand = (int)(Math.random() * 2);
        switch (rand) {
            case 0: return new Orc();
            case 1: return new Drake();
            default: return new Orc();
        }
    }

    public static void displayPersoData(Hero perso){
        System.out.println("Bourse : " + perso.getGold() + " gold");
        System.out.print("Inventaire : " );
        for(Map.Entry<Item, Integer> entry : perso.getItems().entrySet()){
            System.out.print(entry.getKey().getName() + "(" + entry.getValue() + ")");
        }
        System.out.printf("\nHp : %d / %d, Defense : %d, Strength : %d\n", perso.getActualHp(), perso.getMaxHp(), perso.getEndurance(), perso.getStrength());
        System.out.println();
    }
}
