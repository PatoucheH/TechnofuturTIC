package Utils;

import Entity.Animals;
import Entity.Bird;
import Entity.Cat;
import Entity.Dog;

import java.util.*;

public class ManageAnimals {

    public static Animals createAnimal(Scanner sc){
        int userChoice = Utils.checkUserChoiceIsInt(sc,"Veuillez choisir une animal entre le chat (1), le chien (2) et l'oiseau (3) : ", 3);
        return switch (userChoice) {
            case 1 -> createCat(sc);
            case 2 -> createDog(sc);
            case 3 -> createBird(sc);
            default -> null;
        };
    }

    public static Animals createBaseAnimal(Scanner sc){
        System.out.print("Entrez le nom de l'animal : ");
        String name = sc.nextLine();
        int weight = Utils.checkUserChoiceIsInt(sc, "Entrez le poids de l'animal : ", 100);
        int height = Utils.checkUserChoiceIsInt(sc, "Entrez la taille de l'animal : ", 500);
        String gender = (Utils.checkUserChoiceIsInt(sc, "Entrez le sexe de l'animal\n1. Male\n2. Femelle\n : ", 2) == 1)? "Male" : "Femelle";
        int year = Utils.checkUserChoiceIsInt(sc, "Entrez l'age e l'animal : ", 150);
        return new Animals(name, weight, height, gender, year);
    }

    public static Cat createCat(Scanner sc){
        Animals base =  createBaseAnimal(sc);
        System.out.print("Veuillez entrez les caractéristiques du chat séparé par une ',' : ");
        List<String> characteristics = Arrays.stream(sc.nextLine().split(",")).toList();
        boolean longHair = Utils.checkUserChoiceIsInt(sc, "Entrez si votre chat à de long poisl (1) ou non (2) : ", 2) == 1;
        return new Cat(base.name, base.weight, base.height, base.gender, base.year, characteristics, longHair);
    }

    public static Dog createDog(Scanner sc){
        Animals base =  createBaseAnimal(sc);
        System.out.print("Entrez la couleur du collier du chien : ");
        String collarColor = sc.nextLine();
        boolean isDressed = Utils.checkUserChoiceIsInt(sc, "Entrez si votre chien est dressé (1) ou non (2) : ", 2) == 1;
        System.out.print("Entrez la race du chien : ");
        String breed = sc.nextLine();
        return new Dog(base.name, base.weight, base.height, base.gender, base.year, collarColor, isDressed, breed);
    }

    public static Bird createBird(Scanner sc){
        Animals base =  createBaseAnimal(sc);
        System.out.print("Entrez la couleur du volatile : ");
        String color  = sc.nextLine();
        boolean liveInCage = Utils.checkUserChoiceIsInt(sc, "Entrez si votre oiseau est gardé dans une cage (1) ou une volière (2) : ", 2) == 1;
        return new Bird(base.name, base.weight, base.height, base.gender, base.year, color, liveInCage);
    }

    public static List<Animals> getAllAnimalsLive(HashMap<String, Animals> animalsMap){
        return new ArrayList<>(animalsMap.values());
    }
}
