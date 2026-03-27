package com.games;

import java.util.Scanner;

public class Utils {

    public static int askChoice(Scanner sc, String menu, int limitMenu){
        while(true){
            try{
                System.out.println(menu);
                int choice = Integer.parseInt(sc.nextLine());
                if(choice < 1 || choice > limitMenu){
                    System.out.println("Entrez un nombre compris dans le menu");
                    continue;
                }
                return choice;
            }catch(Exception e){
                System.out.println("Veuillez choisir un chiffre dans le menu");
            }
        }
    }
}
