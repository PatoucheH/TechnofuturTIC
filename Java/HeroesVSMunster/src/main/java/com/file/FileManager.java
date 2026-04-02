package com.file;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.models.*;
import com.models.enums.ItemType;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileManager {

    public static List<String> getFilesName() {
        Path path = Paths.get("saves");
        try (Stream<Path> files = Files.list(path)) {
            List<String> filesList =  files.filter(p -> p.toString().endsWith(".json"))
                    .map(p -> p.getFileName().toString().replace(".json", ""))
                    .toList();
            if (filesList.isEmpty()) {
                return new ArrayList<>();
            }
            return filesList;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    public static Hero getPerso(String heroName){
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode json =  mapper.readTree(new FileReader("saves/" + heroName + ".json"));
            Iterator<Map.Entry<String, JsonNode>> fields = json.fields();
            Map.Entry<String, JsonNode> entry = fields.next();
            String[] classNameArray = entry.getKey().split("\\.");
            String className = classNameArray[classNameArray.length-1];
            JsonNode data = entry.getValue();
            Hero perso;
            if(className.equals("Dwarf")){
                perso = new Dwarf(data.get("name").asText());
            }else if(className.equals("Human")){
                perso = new Human(data.get("name").asText());
            }else{
                throw new RuntimeException("Pas de héro dans cette sauvegarde");
            }
            perso.setMaxHp(data.get("maxHp").asInt());
            perso.setActualHp(data.get("actualHp").asInt());
            perso.setEndurance(data.get("endurance").asInt());
            perso.setStrength(data.get("strength").asInt());
            perso.setXp(data.get("xp").asInt());
            perso.setLevel(data.get("level").asInt());
            perso.setGold(data.get("gold").asInt());
            perso.setPosition(new Position(data.get("position").get("x").asInt(), data.get("position").get("y").asInt()));
            HashMap<ItemType, Integer> items = new HashMap<>();
            data.get("items").fields().forEachRemaining(i -> {
                items.put(ItemType.valueOf(i.getKey()), i.getValue().asInt());
            });
            perso.setItems(items);
            HashMap<String, ItemType> equipment = new HashMap<>();
            data.get("equipment").fields().forEachRemaining(i -> {
                String value = i.getValue().asText();
                if (value != null && !value.equals("null") && !value.isEmpty()) {
                    try {
                        equipment.put(
                                i.getKey(),
                                ItemType.valueOf(value.toUpperCase())
                        );
                    } catch (IllegalArgumentException e) {
                        System.out.println("Valeur invalide pour ItemType : " + value);
                    }
                } else System.out.println("Valeur null pour la clé : " + i.getKey());
            });
            perso.setEquipment(equipment);
            return perso;
        }catch(JsonProcessingException e){
            System.out.println(e);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void deleteSave(String heroName){
        Path path = Paths.get("saves/" + heroName + ".json");

        try{
            boolean deleted = Files.deleteIfExists(path);
            if(deleted) System.out.println("Sauvegarde supprimé");
            else System.out.println("Pas de sauvegarde supprimé");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
