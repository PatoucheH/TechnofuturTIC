package Array.Exercises;

import java.util.Arrays;

public class Exo4 {
    public static void exo4(){
        int[] ints = {10, 7, 2, 12, 9, 1};
        System.out.println("Before sort " + Arrays.toString(ints));
        for (int i = 0; i < ints.length - 1; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                if(ints[i] > ints[j]) {
                    int tmp = ints[i];
                    ints[i] = ints[j];
                    ints[j] = tmp;
                }
            }
        }
        System.out.println("After sort " + Arrays.toString(ints));
    }
}

