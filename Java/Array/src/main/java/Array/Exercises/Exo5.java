package Array.Exercises;

import java.util.Arrays;
import java.util.Scanner;

public class Exo5 {
    public static void exo5(){
        Scanner sc = new Scanner(System.in);
        String[][] table2D = new  String[6][6];
        table2D[0] = new String[]{"♟️", " ", " ", " ", " ", " "};
        table2D[1] = new String[]{" ", " ", " ", " ", " ", " "};
        table2D[2] = new String[]{" ", " ", " ", " ", " ", " "};
        table2D[3] = new String[]{" ", " ", " ", " ", " ", " "};
        table2D[4] = new String[]{" ", " ", " ", " ", " ", " "};
        table2D[5] = new String[]{" ", " ", " ", " ", " ", " "};
        int[] positionPion = {0, 0};
        boolean finish = false;
        while(!finish){
            printStringArray(table2D);

            System.out.println("\nPlease press : \n'L' (To move to left)\n'R' (To move to right)\n'B' (To move to the bottom\n" +
                    "'U' (To move to the upper  \nAnything else to left");
            String choice = sc.nextLine();
            if(!choice.equalsIgnoreCase("L") && !choice.equalsIgnoreCase("R") &&
                !choice.equalsIgnoreCase("B") && !choice.equalsIgnoreCase("U")){
                    finish = true;
            }
            if(choice.equalsIgnoreCase("u")){
                if(positionPion[0] == 0){
                    System.out.println("You are on the edge of the map ");
                }else{
                    String tmp =  table2D[positionPion[0]][positionPion[1]];
                    table2D[positionPion[0]][positionPion[1]] = "⬛ ";
                    table2D[--positionPion[0]][positionPion[1]] = tmp;
                }
            }else if(choice.equalsIgnoreCase("b")) {
                if (positionPion[0] >= table2D[0].length - 1) {
                    System.out.println("You are on the edge of the map ");
                } else {
                    String tmp = table2D[positionPion[0]][positionPion[1]];
                    table2D[positionPion[0]][positionPion[1]] = "⬛ ";
                    table2D[++positionPion[0]][positionPion[1]] = tmp;
                }
            }else if(choice.equalsIgnoreCase("r")) {
                if (positionPion[1] >= table2D[1].length - 1) {
                    System.out.println("You are on the edge of the map ");
                } else {
                    String tmp = table2D[positionPion[0]][positionPion[1]];
                    table2D[positionPion[0]][positionPion[1]] = "⬛ ";
                    table2D[positionPion[0]][++positionPion[1]] = tmp;
                }
            }else if(choice.equalsIgnoreCase("l")) {
                if (positionPion[1] == 0) {
                    System.out.println("You are on the edge of the map ");
                } else {
                    String tmp = table2D[positionPion[0]][positionPion[1]];
                    table2D[positionPion[0]][positionPion[1]] = "⬛ ";
                    table2D[positionPion[0]][--positionPion[1]] = tmp;
                }
            }
        }
    }


    public static void printStringArray(String[][] aray){
        for (String[] strings : aray) {
            for (String string : strings) {
                if(string.equals(" ")) System.out.print("⬛ ");
                else System.out.print(string + " ");
            }
            System.out.println();
        }
    }
}
