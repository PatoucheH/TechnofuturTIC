package Utils;

import java.util.List;

public class Menu {

    public static String mainMenu(){
        return "1. Ajouter un animal à l'animalerie\n2. Lister tout les animaux vivants\n3. Passer la nuit\n" +
                "4. Afficher les animaux par type\n5. Sauvegarder la partie\n6. Quitter\n";
    }

    public static String startMenu(List<String> saves) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Sauvegardes disponibles ---\n");
        for (int i = 0; i < saves.size(); i++) {
            sb.append(i + 1).append(". Charger \"").append(saves.get(i)).append("\"\n");
        }
        sb.append(saves.size() + 1).append(". Nouvelle partie\n");
        return sb.toString();
    }
}
