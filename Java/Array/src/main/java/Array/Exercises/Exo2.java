package Array.Exercises;

import java.util.Scanner;

public class Exo2 {
    public static void exo2(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez un nombre : ");
        int n = scanner.nextInt();
        int size = n * 2 + 1;
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                int distance = Math.min(Math.min(i, j), Math.min(size - 1 - i, size - 1 - j));
                int value = n - distance;
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
