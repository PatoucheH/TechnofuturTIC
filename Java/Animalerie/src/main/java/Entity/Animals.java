package Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.LocalDateTime;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cat.class, name = "cat"),
        @JsonSubTypes.Type(value = Dog.class, name = "dog"),
        @JsonSubTypes.Type(value = Bird.class, name = "bird")
})

public class Animals {
    public String name;
    public int weight;
    public int height;
    public String gender;
    public int year;
    private int humanYear;
    public LocalDateTime arrivedDate;
    @JsonIgnore
    private double deadPossibility;

    public Animals(){}
    public Animals(String name, int weight, int height, String gender, int year){
        this();
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.gender = gender;
        this.year = year;
        this.arrivedDate = LocalDateTime.now();
    }

    public void setHumanYear(int humanYear){
        this.humanYear = humanYear;
    }

    public void setDeadPossibility(double deadPossibility) {
        this.deadPossibility = deadPossibility;
    }

    public double getDeadPossibility() {
        return deadPossibility;
    }

    public String crier(){
        return "Bruit d'animaux";
    }
}
