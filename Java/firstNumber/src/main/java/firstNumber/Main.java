package firstNumber;

public class Main {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        int compteur = 1;
        int nb = 2;

        while (compteur < 1_000_000) {
            if (Main.isFirst(nb)){
//                System.out.println(compteur + ") " + nb);
                compteur++;
            }
            nb++;
        }
        long end = System.currentTimeMillis();
        System.out.println(compteur + ") " + nb);
        System.out.println("Temps = " + (end - start) + " ms");
    }

    public static boolean isFirst(int number) {
        if (number % 2 == 0) return false;
        for (int i = 3; i <= Math.sqrt(number); i += 2) if(number % i == 0) return false;
        return true;
    }
}
