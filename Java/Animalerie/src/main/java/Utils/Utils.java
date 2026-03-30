package Utils;

import java.util.Scanner;

public class Utils {
    public static int checkUserChoiceIsInt(Scanner sc, String menu, int limit){
        while(true){
            System.out.print(menu);
            String userChoice = sc.nextLine();
            try {
                int choice = Integer.parseInt(userChoice);
                if (choice >= 1 && choice <= limit) return choice;
                else System.out.println("Entrée invalide. Veuillez entrer un nombre entre 1 et " + limit);
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre.");
            }
        }
    }
}
