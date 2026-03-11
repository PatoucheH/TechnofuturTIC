package Array.Exercises;

import java.util.Arrays;

public class Exo3 {
    public static void exo3(){
        String[] strings = new String[100];

        for (int i = 0; i < strings.length; i++) {
            if(i % 5 == 0 && i != 0) strings[i] = "Ding-Ding";
            if (i % 7 == 0 && i != 0) strings[i] = "Bottle";
            if(i % 7 == 0 && i % 5 == 0 && i != 0) strings[i] = "Ding-Ding Bottle";
            if(i % 7 != 0 && i % 5 != 0) strings[i] = String.valueOf(i);
            if(i == 0) strings[i] = String.valueOf(i);
        }
        System.out.println(Arrays.toString(strings));

    }
}
