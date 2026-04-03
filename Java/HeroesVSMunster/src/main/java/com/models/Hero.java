package com.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.models.enums.Dice;
import com.models.enums.ItemType;

import java.util.*;

import static com.models.enums.Dice.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.CLASS,
        include = JsonTypeInfo.As.WRAPPER_OBJECT
)
public abstract class Hero extends Charactr {
    private int xp;
    private int level;
    private int gold;
    private int armor;
    private int mapPassed;
    private Position position;
    private HashMap<ItemType, Integer> items;
    private HashMap<String, ItemType> equipment = new HashMap<>();

    public Hero() {}
    public Hero(String name, int gold, int endurance, int strength) {
        int hp;
        if(endurance < 5) hp = endurance -1;
        else if(endurance < 10) hp = endurance;
        else if(endurance < 15) hp = endurance + 1;
        else hp = endurance + 2;
        super(name, hp + 10, endurance, strength );
        setGold(gold);
        setXp(0);
        setLevel(1);
        setItems(new HashMap<>());
        setPosition( new Position(0, 0));
        equipment.put("weapon", null);
        equipment.put("helmet", null);
        equipment.put("armor", null);
        setArmor();
    }

    public int getXp() {
        return xp;
    }
    public int getLevel() {
        return level;
    }
    public int getGold() {
        return gold;
    }
    public HashMap<ItemType, Integer> getItems() {
        return items;
    }
    public Position getPosition() {
        return position;
    }
    public HashMap<String, ItemType> getEquipment() {
        return equipment;
    }
    public int getArmor() {
        return armor;
    }
    public int getMapPassed() {
        return mapPassed;
    }

    public void setGold(int gold) {
        this.gold = Math.max(gold, 0);
    }
    public void setPosition(Position position) {
        this.position = position;
    }
    public void setXp(int xp){
        this.xp = xp;
    }
    public void setLevel(int level){
        this.level = level;
    }
    public void setItems(HashMap<ItemType, Integer> items){
        this.items = items;
    }
    public void setEquipment(HashMap<String, ItemType> equipment){
        this.equipment = equipment;
    }
    public void setArmor(){
        this.armor = 0;
        if(equipment.containsValue(ItemType.ARMOR)) this.armor += D4.roll();
        if(equipment.containsValue(ItemType.HELMET)) this.armor += D2.roll();
    }
    public void setMapPassed(int mapPassed) {
        this.mapPassed = mapPassed;
    }

    public void addItem(ItemType item, int nbr) {
        if (nbr <= 0) return;
        this.items.put(item, this.items.getOrDefault(item, 0) + nbr);
    }

    public void addGold(int gold) {
        if(gold > 0) {
            this.gold += gold;
        }
    }

    public int removeGold(int gold){
        if(gold > 0){
            this.gold -= gold;
            return getGold();
        }else{
            System.out.println("Doit retirer un nombre positif de gold ");
            return 0;
        }
    }

    public String  gainStat(){
        int hpWin = Dice.D20.roll();
        int strengthWin = D4.roll();
        int enduranceWin = D4.roll();
        this.setMaxHp(this.getMaxHp() + hpWin);
        this.setStrength(this.getStrength() + strengthWin);
        this.setEndurance(this.getEndurance() + enduranceWin);
        return "\nVous avez gagné " + hpWin + " hp, " + strengthWin + " de force et " + enduranceWin + " d'endurance";
    }

    private String addLevel(){
        String resultStat = gainStat();
        setActualHp(getMaxHp());
        return "\nVous êtes monté au niveau : " + ++this.level +  "\n" + resultStat;
    }

    public String addXp(int xp) {
        String returnValue =  "";
        this.xp += Math.max(xp, 0);
        returnValue = "\nVous avez gagné : " + xp + " d'xp";
        if(this.xp >= getLevel() * 10) {
            this.xp -= getLevel() * 10;
            returnValue += addLevel();
        }
        return returnValue;
    }

    public boolean usePotion() {
        if (this.items.containsKey(ItemType.POTION)) {
            int quantity = this.items.get(ItemType.POTION);
            if (quantity > 1) {
                this.items.put(ItemType.POTION, quantity - 1);
            } else {
                this.items.remove(ItemType.POTION);
            }
            int heal = Math.max(Dice.D20.roll(), 5);
            setActualHp(Math.min(getMaxHp(), getActualHp() + heal ));
            System.out.printf("Vous avez soigné %d et avez actuellement %d points de vie\n", heal, getActualHp());
            return true;
        }else {
            return false;
        }
    }

    public void openEquipment() {
        System.out.println("Votre equipement : ");
        for(Map.Entry<String, ItemType> entry : equipment.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void addEquipment(Scanner sc) {
        int itemEquipment = 0;
        for (Map.Entry<ItemType, Integer> entry : items.entrySet()) {
            if (entry.getKey() == ItemType.SWORD || entry.getKey() == ItemType.ARMOR
                    || entry.getKey() == ItemType.HELMET) {
                System.out.println(entry.getKey() + " : " + entry.getValue());
                itemEquipment++;
            }
        }
        if (itemEquipment <= 0) {
            System.out.println("Pas d'quipement dans votre inventaire");
            sc.nextLine();
            return;
        }
        System.out.println("Taper le nom de l'équipement à équiper ou 0 pour quitter : ");
        String userChoice = sc.nextLine();
        if (userChoice.equals("0")) return;
        if (userChoice.equalsIgnoreCase(ItemType.SWORD.toString())) {
            items.remove(ItemType.SWORD);
            equipment.put("weapon", ItemType.SWORD);
            System.out.println("Vous vous équipez d'une épée");
        } else if (userChoice.equalsIgnoreCase(ItemType.ARMOR.toString())) {
            items.remove(ItemType.ARMOR);
            equipment.put("armor", ItemType.ARMOR);
            System.out.println("Vous vous équipez d'une armure");
        } else if(userChoice.equalsIgnoreCase(ItemType.HELMET.toString())){
            items.remove(ItemType.HELMET);
            equipment.put("helmet", ItemType.HELMET);
            System.out.println("Vous vous équipez d'un casque");
        }else{
            System.out.println("Pas d'équipement de ce nom");
        }
        setArmor();
    }

    public void openInventory(){
        for(Map.Entry<ItemType, Integer> entry : items.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void useItem(ItemType item){
        if(item.equals(ItemType.POTION)){
            usePotion();
        }else{
            System.out.println("Impossible d'utiliser cet item maintenant ");
        }
    }

    @Override
    public void getDamage(int damage){
        setArmor();
        if(damage < 0) throw new IllegalArgumentException("Les dommages doivent être positifs");
        setActualHp(getActualHp() - (Math.max((damage - armor), 0)));
    }

    @Override
    public int attack(){
        int bonus = 0;
        if(equipment.containsValue(ItemType.SWORD)) bonus = D4.roll();
        if(getStrength() < 5) return D4.roll() - 1 + bonus;
        else if(getStrength() < 10) return D4.roll() + bonus;
        else if(getStrength() < 15) return D4.roll() + 1 + bonus;
        else return D4.roll() + 2 + bonus;
    }
}
