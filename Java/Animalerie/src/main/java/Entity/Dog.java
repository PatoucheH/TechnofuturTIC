package Entity;

public class Dog extends Animals{
    public Color collarColor;
    public boolean isDressed;
    public String breed;

    public Dog(){
        setDeadPossibility(0.01);
    }

    public Dog(String name, int weight, int height, GenderType genderType, int year, Color collarColor, boolean isDressed, String breed) {
        super(name, weight, height, genderType, year);
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
