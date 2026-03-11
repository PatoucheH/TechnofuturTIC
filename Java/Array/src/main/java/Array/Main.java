package Array;

import Array.Exercises.*;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        boolean finish = false;
//        int[][] matrice = {
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9},
//        };
//        int[][] matrice = {
//                {5, 5, 5},
//                {5, 5, 5},
//                {5, 5, 5}
//        };
        int[][] matrice = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

        while (!finish) {
            System.out.println("Please Choose the number of the exercise you want to display");
            System.out.println("1. Print array of index\n2. Print a cube of number\n3. Ding-Ding Bottle\n4. Sort array" +
                    "\n5. Move the pion\n6. Magic cube\n7. Rotate matrice\n8. Exit");
            System.out.println();
            String choice = sc.next();
            switch (choice) {
                case "1":
                    Exo1.exo1();
                    System.out.println();
                    break;
                case "2":
                    Exo2.exo2();
                    System.out.println();
                    break;
                case "3":
                    Exo3.exo3();
                    System.out.println();
                    break;
                case "4":
                    Exo4.exo4();
                    System.out.println();
                    break;
                case "5":
                    Exo5.exo5();
                    System.out.println();
                    break;
                case "6":
                    Exo6.exo6(matrice);
                    System.out.println();
                    break;
                case "7":
                    Exo7.exo7(matrice);
                    System.out.println();
                    break;
                case "8":
                    System.out.println("Exit");
                    finish = true;
                    break;
                default:
                    break;
            }
        }
    }
}