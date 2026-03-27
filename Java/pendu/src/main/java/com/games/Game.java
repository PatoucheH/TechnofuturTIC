package com.games;

import com.FileManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class Game {

    public static void game() {
        String userCategory = "";
        System.out.println("Bienvenue dans le jeu du pendu");
        Scanner sc = new Scanner(System.in);
        while(true){
            if(!userCategory.isEmpty()) System.out.println("Vous avez choisi la catégorie : " + userCategory);
            int userChoice = Utils.askChoice(sc, Menu.MainMenu(), 6);

            switch (userChoice) {
                case 1:
                    userCategory = chooseCategory(sc);
                    break;
                case 2:
                    addCategory(sc);
                    break;
                case 3:
                    userCategory = deleteCategory(sc, userCategory);
                    break;
                case 4:
                    if(userCategory.isEmpty()) System.out.println("Choisissez une catégorie avant de l'éditer");
                    else editCategory(sc, userCategory);
                    break;
                case 5:
                    String word;
                    // Choose word random
                    if(userCategory.isEmpty()) word = FileManager.getRandomWord();
                    else word = FileManager.getRandomWord(userCategory);
                    // Check if category choosen get a word
                    if(word == null){
                        System.out.println("Il n'y a pas de mots dans cette catégorie choisissez en une autre ");
                    }
                    else{
                        int difficultyChoose = Utils.askChoice(sc, Menu.difficultyChoose(), 3);
                        switch (difficultyChoose) {
                            case 1:
                                play(sc,word, 15);
                                break;
                            case 2:
                                play(sc,word, 8);
                                break;
                            case 3:
                                play(sc,word, 4);
                                break;
                        }
                    }
                    break;
                case 6:
                    return;
                default:
                    break;
            }
        }
    }

    private static String chooseCategory(Scanner sc){
        List<String> categories = FileManager.getFilesName();
        while(true){
            System.out.print("Veuillez choisir une categorie en entrant son nom (");
            for (int i = 0; i < categories.size(); i++) {
                if(categories.size() - 1 == i) System.out.println(categories.get(i) + ")");
                else System.out.print(categories.get(i) + ", ");
            }
            String userChoice = sc.nextLine();
            for (int i = 0; i < categories.size(); i++) {
                if(userChoice.equals(categories.get(i))){
                    return userChoice;
                }
            }
            System.out.println("Aucune catégorie ne correspond !");
        }
    }

    private static void addCategory(Scanner sc){
        System.out.println("Entrer le nom de la catégorie à ajouter");
        while(true){
            String userChoice =  sc.nextLine();
            if(userChoice.isEmpty() ){
                System.out.println("Ne laisser pas le champ vide");
            }else if(FileManager.getFilesName().contains(userChoice)){
                System.out.println("La catégorie existe déjà");
            }else{
                FileManager.createCategory(userChoice);
                return;
            }
        }
    }

    private static String deleteCategory(Scanner sc, String userCategory){
        System.out.println("Entrer le nom de la catégorie à supprimer" + FileManager.getFilesName());
        while(true){
            String userChoice =  sc.nextLine();
            if(userChoice.isEmpty() ){
                System.out.println("Ne laisser pas le champ vide");
            }else if(!FileManager.getFilesName().contains(userChoice)){
                System.out.println("La catégorie n'existe pas");
            }else if(FileManager.getFilesName().contains(userChoice)){
                FileManager.deleteCategory(userChoice);
                if(userChoice.equals(userCategory)) return "";
                return userCategory;
            }else{
                System.out.println("putain de problème ! ");
            }
        }
    }

    private static void editCategory(Scanner sc, String category){
        System.out.println("Que faire dans la catégorie " + category);
        int userChoice = Utils.askChoice(sc, Menu.whatEditMenu(), 3);
        switch(userChoice){
            case 1:
                FileManager.addWord(sc, category);
                break;
            case 2:
                FileManager.deleteWord(sc, category);
                break;
            case 3:
                return;
            default:
                break;
        }
    }

    private static void play(Scanner sc, String word, int playerLife){
        int length = word.length();
        char[] wordToPrint = new char[length];
        HashSet<Character> letterUsed = new HashSet<>();
        Arrays.fill(wordToPrint, '_');
        boolean isInWord = false;
        while(true){
            isInWord = false;
            try{
                for (int i = 0; i < length; i++) {
                    if(word.charAt(i) == ' ')wordToPrint[i] = ' ';
                    System.out.print(wordToPrint[i] + " ");
                }
                System.out.printf("\nIl vous reste %d vie(s)", playerLife);
                System.out.print("\nEntrez une lettre : ");
                String letter = sc.nextLine();
                if(letter.length() != 1 || !Character.isLetter(letter.charAt(0))) {
                    System.out.println("S'il vous plait entrer juste une lettre");
                    continue;
                }
                char c = letter.charAt(0);
                for (int i = 0; i < length; i++) {
                    if(c == word.toLowerCase().charAt(i)){
                        wordToPrint[i] = c;
                        isInWord = true;
                    }
                }
                if(!isInWord){
                    letterUsed.add(c);
                    playerLife--;
                }
                if(playerLife <= 0){
                    System.out.println("Perdu vous n'avez plus de vies\n");
                    return;
                }
                if(new String(wordToPrint).equals(word)){
                    System.out.println("GAGNE !!!\nLe mot était " + word);
                    return;
                }
                System.out.println("Mauvaise lettre utilisé : " + letterUsed);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
