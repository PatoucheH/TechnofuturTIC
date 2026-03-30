package Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

public class PetStore {
    public String name;
    @JsonProperty("aliveAnimals")
    public HashMap<String, Animals> animals;
    public HashMap<String, Animals> deadAnimals;

    public PetStore() {}

    public PetStore(String name){
        this.name = name;
        this.animals = new HashMap<>();
        this.deadAnimals = new HashMap<>();
    }

    public void addAnimal(Animals animal){
        this.animals.put(UUID.randomUUID().toString(), animal);
    }

    private void killAnimal(Animals animal){
        String keyToRemove = null;
        for(Map.Entry<String, Animals> entry : animals.entrySet()){
            if(entry.getValue().equals(animal)){
                keyToRemove = entry.getKey();
                break;
            }
        }
        if(keyToRemove != null){
            this.animals.remove(keyToRemove);
            this.deadAnimals.put(UUID.randomUUID().toString(), animal);
        }
    }

    @JsonIgnore
    public HashMap<String, Integer> getAnimalsByType() {
        HashMap<String, Integer> animalsByType = new HashMap<>();
        for(Animals animal : animals.values()){
            if(animal instanceof Dog)animalsByType.put("dog",animalsByType.getOrDefault("dog", 0) + 1);
            else if(animal instanceof Cat)animalsByType.put("cat",animalsByType.getOrDefault("cat", 0) + 1);
            else if(animal instanceof Bird) animalsByType.put("bird",animalsByType.getOrDefault("bird", 0) + 1);
        }
        return animalsByType;
    }

    @JsonIgnore
    public HashMap<String, Animals> getLiveAnimals(){
        return this.animals;
    }

    public HashMap<String, Animals> getDeadAnimals(){
        return this.deadAnimals;
    }

    public void passNight(){
        Random rand = new Random();
        List<Animals> animalsToKill = new ArrayList<>();
        for(Animals animal : this.animals.values()){
            double randomValue = rand.nextDouble();
            if(randomValue < animal.getDeadPossibility()){
                animalsToKill.add(animal);
            }
        }
        for(Animals animal : animalsToKill){
            System.out.println(animal.name + " is dead");
            killAnimal(animal);
        }
    }
}
