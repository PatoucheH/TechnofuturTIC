package Entity;

public class Dog extends Animals{
    public String collarColor;
    public boolean isDressed;
    public String breed;

    public Dog(){
        setDeadPossibility(0.01);
    }

    public Dog(String name, int weight, int height, String gender, int year, String collarColor, boolean isDressed, String breed) {
        super(name, weight, height, gender, year);
        this.setDeadPossibility(0.01);
        this.setHumanYear(year * 10);
        this.collarColor = collarColor;
        this.isDressed = isDressed;
        this.breed = breed;
    }

    @Override
    public String crier(){
        return "Wouf";
    }
}
