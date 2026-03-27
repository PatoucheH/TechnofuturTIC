package Models;

import java.util.Date;
import java.util.List;

public class Course {
    private final String _name;
    private List<Student> _students;
    private final Trainer _trainer;
    private List<Date> _datesOfCourse;

    public Course(String name, Trainer trainer) {
        _name = name;
        _trainer = trainer;
    }

    // GETTERS
    public String getName() {
        return _name;
    }

    public List<Student> getStudents() {
        return _students;
    }

    public Trainer getTrainer() {
        return _trainer;
    }

    public List<Date> getDatesOfCourse() {
        return _datesOfCourse;
    }

    // SETTERS/ADDERS ????
    public void addStudents(List<Student> students) {
        _students.addAll(students);
    }
    public void addDatesOfCourse(List<Date> datesOfCourse) {
        _datesOfCourse = datesOfCourse;
    }


}
