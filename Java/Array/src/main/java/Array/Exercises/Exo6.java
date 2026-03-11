package Array.Exercises;

public class Exo6 {
    public static void exo6(int[][] matrice){

        boolean isMagic = isMagic(matrice);
        if(isMagic) System.out.println("Yes IS fucking MAgic");
        else System.out.println("NOPE");
    }

    public static boolean isMagic(int[][] matrice) {
        if (matrice.length != matrice[0].length) return false;
        int size = matrice.length;
        int targetSum = 0;
        for (int i = 0; i < size; i++) targetSum += matrice[0][i];

        for (int i = 0; i < size; i++) {
            int totLine = 0;
            for (int j = 0; j < size; j++) {
                totLine += matrice[i][j];
            }
            if(targetSum != totLine) return false;{}
        }
        for (int j = 0; j < size; j++) {
            int totCol = 0;
            for (int i = 0; i < size; i++) {
                totCol += matrice[i][j];
            }
            if (totCol != targetSum) return false;
        }

        int diag1 = 0;
        for (int i = 0; i < size; i++) {
            diag1 += matrice[i][i];
        }
        if (diag1 != targetSum) return false;

        int diag2 = 0;
        for (int i = 0; i < size; i++) {
            diag2 += matrice[i][size - 1 - i];
        }
        if (diag2 != targetSum) return false;

        return true;
    }
}
