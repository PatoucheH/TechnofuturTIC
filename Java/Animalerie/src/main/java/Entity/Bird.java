package Entity;

public class Bird extends Animals{
    public Color color;
    public boolean liveInCage;

    public Bird(){
        setDeadPossibility(0.03);
    }
    public Bird(String name, int weight, int height, GenderType genderType, int year, Color color, boolean liveInCage) {
        super(name, weight, height, genderType, year);
        this.setDeadPossibility(0.03);
        this.setHumanYear(year * 10);
        this.color = color;
        this.liveInCage = liveInCage;
    }

    public String crier(){
        return "Piou Piou";
    }
}
