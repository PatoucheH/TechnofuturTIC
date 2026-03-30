import Entity.Animals;
import Entity.PetStore;
import Utils.*;

import java.util.List;

void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    PetStore store;
    String name;

    System.out.println("Bienvenue dans le gestionnaire d'animalerie !\n");
    List<String> listOfSaves = FileManager.listSaves();
    int choiceSaveOrNew = 0;

    if(listOfSaves.isEmpty()) System.out.println("Aucune sauvegarde trouvé");
    else choiceSaveOrNew = Utils.checkUserChoiceIsInt(sc, "Voulez vous charger une partie existante (1) ou créer une nouvelle partie (2) ? ", 2);
    if(choiceSaveOrNew == 1 ) {
        String loadChoose = "";
        while(true){
            System.out.println("Quelle partie chargé ? ");
            for (String file : listOfSaves){
                System.out.println("- " + file);
            }
            loadChoose =  sc.nextLine();
            String finalLoadChoose = loadChoose;
            if(listOfSaves.stream().anyMatch(s -> s.equalsIgnoreCase(finalLoadChoose)) ){
                break;
            }else {
                System.out.println("Sauvegarde introuvable");
            }
        }
        store = FileManager.load(loadChoose);
    }else{
        while (true) {
            System.out.print("Veuillez choisir un nom pour votre animalerie : ");
            name = sc.nextLine();
            if (name.length() < 3) System.out.println("Le nom doit être de minimum 3 caractères");
            else break;
        }
        store = new PetStore(name);
    }

    List<Animals> animalsList;
    while(true){
        System.out.println("\nVous êtes dans l'animalerie : " + store.name);
        int userChoice = Utils.checkUserChoiceIsInt(sc, Menu.mainMenu(), 6);
        switch(userChoice){
            case 1:
                Animals animals = ManageAnimals.createAnimal(sc);
                store.addAnimal(animals);
                break;
            case 2:
                animalsList = ManageAnimals.getAllAnimalsLive(store.getLiveAnimals());
                for(Animals a : animalsList){
                    System.out.println("- " + a.name);
                }
                break;
            case 3:
                store.passNight();
                break;
            case 4:
                System.out.println(store.getAnimalsByType());
                break;
            case 5:
                FileManager.save(store, store.name);
                break;
            case 6:
                return;
            default:
                break;
        }
    }
}

