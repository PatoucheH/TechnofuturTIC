package com.games;

import java.util.Scanner;

public class Menu {
    public static String MainMenu() {
        return "Veuillez choisir un choix dans le menu suivant\n" +
                "1. Choisir une catégorie\n" +
                "2. Ajouter une catégorie\n" +
                "3. Supprimer une catégorie\n" +
                "4. Editer une catégorie\n" +
                "5. Jouer\n" +
                "6. Quitter";
    }

    public static String whatEditMenu() {
        return "1. Ajoutez un mot\n2. Supprimer un mot\n3. Quitter";
    }

    public static String difficultyChoose(){
        return "1. Facile (15 vies)\n2. Normal (8 vies)\n3. Difficile (4 vies)";
    }

}