package com.game;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.models.*;
import com.models.enums.ItemType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
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
                System.out.println("Erreur : Ce n'est pas un nombre entier valide");
            }
        }
    }

    public static char askMove(Scanner sc) {
        while (true) {
            System.out.print("ZQSD pour bouger ou E pour sauvegarder : ");
            String input = sc.nextLine().trim().toLowerCase();
            if (input.length() == 1) {
                char c = input.charAt(0);
                if (c == 'z' || c == 'q' || c == 's' || c == 'd' || c == 'e') {
                    return c;
                }
            }
            System.out.println("Entrée invalide ! Utilise uniquement Z Q S D");
        }
    }

    public static Monster randomMonster() {
        int rand = (int)(Math.random() * 3);
        return switch (rand) {
            case 1 -> new Drake();
            case 2 -> new Gobelins();
            default -> new Orc();
        };
    }

    public static void displayPersoData(Hero perso){
        System.out.println("Bourse : " + perso.getGold() + " gold");
        System.out.print("Inventaire : " );
        for(Map.Entry<ItemType, Integer> entry : perso.getItems().entrySet()){
            System.out.print(entry.getKey() + "(" + entry.getValue() + ")");
        }
        System.out.printf("\nHp : %d / %d, Defense : %d, Strength : %d\n", perso.getActualHp(), perso.getMaxHp(), perso.getEndurance(), perso.getStrength());
        System.out.println();
    }

    public static int askSaveOrNew(Scanner sc){
        while (true){
            System.out.println("Voulez-vous charger une partie (1) ou créer une nouvelle partie (2) ? ");
            String userChoice = sc.nextLine();
            try{
                int choice = Integer.parseInt(userChoice);
                if(choice == 1) return 1;
                if(choice == 2) return 2;
            } catch (NumberFormatException e) {
                System.out.println("Erreur : ce n'est pas un nombre valide 1 ou 2 ");
            }
        }
    }

    public static void save(Charactr perso){
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File dir = new File("saves");
        if (!dir.exists()) dir.mkdirs();
        try {
            mapper.writeValue(Paths.get("saves", perso.getName() + ".json").toFile(), perso);
            System.out.println("Sauvegarde \"" + perso.getName() + "\" créée");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int selectUserSave(Scanner sc, List<String> listSave){
        while(true){
            System.out.println("Choisissez une sauvegarde parmis celle-ci (Entrez le numéro correspondant)");
            for (int i = 0; i < listSave.size(); i++) {
                System.out.printf("%d) %s\n", i + 1, listSave.get(i));
            }
            String userChoice = sc.nextLine().trim().toLowerCase();
            try {
                int userNombre = Integer.parseInt(userChoice);
                if (userNombre >= 1 && userNombre <= listSave.size()) {
                    return userNombre - 1;
                } else {
                    System.out.printf("Erreur : Le nombre doit être compris entre 1 et %d\n", listSave.size() + 1);
                }
            } catch (NumberFormatException e) {
                System.out.println("Erreur : Ce n'est pas un nombre entier valide");
            }
        }
    }
}
