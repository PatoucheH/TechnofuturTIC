package com.models;

import com.models.enums.ItemType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static com.models.enums.ItemType.*;

public class Seller {
    private HashMap<ItemType, Integer> items = new HashMap<>();
    private Position position;

    public Seller() {}
    public Seller(List<ItemType> itemsToAdd) {
        for (ItemType item : itemsToAdd) {
            int cost = 0;
            switch (item) {
                case LEATHER -> cost = 1;
                case POTION -> cost = 3;
                case SWORD -> cost = 10;
                case ARMOR -> cost = 15;
            }
            this.items.put(item, cost);
        }
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void sellItem(Scanner sc, Hero perso){
        System.out.println("Voici ce que j'ai à vendre : ");
        int i = 1;
        for (Map.Entry<ItemType, Integer> item : items.entrySet()) {
            System.out.println(i++ + ") " + item.getKey());
        }
        while(true){
            System.out.println("\nQue voulez-vous acheter ? (ou appuyer sur 0 pour quitter) ");
            String userChoice = sc.nextLine();
            try{
                int choice = Integer.parseInt(userChoice);
                if(choice == 0) return;
                else if(choice >= 1 && choice < items.size()) {
                    int realChoice = choice - 1;
                    if (perso.getGold() >= (Integer) items.values().toArray()[realChoice]) {
                        perso.removeGold((Integer) items.values().toArray()[realChoice]);
                        perso.addItem((ItemType) items.keySet().toArray()[realChoice], 1);
                        System.out.println("Vous avez acheté " + items.keySet().toArray()[realChoice].toString());
                    } else {
                        System.out.println("Vous ne possèdez pas assez d'argent pour cet item");
                    }
                }
            }catch(Exception e){
                System.out.println("Entrez un numéro dans la liste de ce que j'ai à vendre");
            }
        }
    }
}
