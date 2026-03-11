package Array.Exercises;

public class Exo7 {
    public static void exo7(int[][] matrice){
        int n = matrice.length;
        System.out.println("Before : ");
        printIntArray(matrice);
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrice[i][j];
                matrice[i][j] = matrice[j][i];
                matrice[j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrice[i][j];
                matrice[i][j] = matrice[i][n - 1 - j];
                matrice[i][n - 1 - j] = temp;
            }
        }
        System.out.println("After : ");
        printIntArray(matrice);
    }
        public static void printIntArray(int[][] aray){
            for (int[] ints : aray) {
                for (int nb : ints) {
                    System.out.print(nb + ", ");
                }
                System.out.println();
            }
        }
}
