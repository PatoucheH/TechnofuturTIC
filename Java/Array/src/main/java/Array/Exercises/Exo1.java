package Array.Exercises;
import java.util.Arrays;

public class Exo1 {

    public static void exo1(){
        int[] ints = new int[10];
        for(int i = 0; i < ints.length; i++) {
            ints[i] = i;
        }
        System.out.println(Arrays.toString(ints));
    }

}
