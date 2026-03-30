package Utils;

import Entity.PetStore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String SAVES_DIR = "saves";

    public static void save(PetStore petStore, String saveName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        File dir = new File(SAVES_DIR);
        if (!dir.exists()) dir.mkdirs();
        try {
            mapper.writeValue(Paths.get(SAVES_DIR, saveName + ".json").toFile(), petStore);
            System.out.println("Sauvegarde \"" + saveName + "\" créée");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PetStore load(String saveName) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        try {
            File file = Paths.get(SAVES_DIR, saveName + ".json").toFile();
            if (!file.exists()) {
                System.out.println("Sauvegarde introuvable");
                return null;
            }
            PetStore store = mapper.readValue(file, PetStore.class);
            System.out.println("Chargement réussi");
            return store;
        } catch (IOException e) {
            System.out.println("Erreur lors du chargement");
            e.printStackTrace();
            return null;
        }
    }

    public static List<String> listSaves() {
        List<String> names = new ArrayList<>();
        File dir = new File(SAVES_DIR);
        if (!dir.exists()) return names;
        File[] files = dir.listFiles((d, name) -> name.endsWith(".json"));
        if (files == null) return names;
        for (File f : files) {
            names.add(f.getName().replace(".json", ""));
        }
        return names;
    }
}
