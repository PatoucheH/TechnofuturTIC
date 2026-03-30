package Entity;

import java.util.ArrayList;
import java.util.List;

public class Cat extends Animals{
    public List<String> caracteristics = new ArrayList<>();
    public boolean trimmedClaws;
    public boolean longHair;

    public Cat(){
        setDeadPossibility(0.005);
    }

    public Cat(String name, int weight, int height, String gender, int year, List<String> caracteristics, boolean longHair) {
        super(name, weight, height, gender, year);
        this.setDeadPossibility(0.005);
        this.setHumanYear(year * 10);
        this.caracteristics = caracteristics;
        this.longHair = longHair;
        this.trimmedClaws = false;
    }

    @Override
    public String crier(){
        return "Miaouw";
    }
}
