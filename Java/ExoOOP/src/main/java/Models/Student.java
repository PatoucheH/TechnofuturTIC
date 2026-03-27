package Models;

import java.util.Date;
import java.util.List;

public class Student extends Person {
    private String _tel;
    private final Date _birthday;
    private List<Course> _courses;

    public Student(String firstName, String name, String email,String tel, Date date){
        super(firstName, name, email);
        _tel = tel;
        _birthday = date;
    }

    // GETTERS
    public String get_tel() {
        return _tel;
    }
    public Date get_birthday() {
        return _birthday;
    }
    public List<Course> getCourses() {
        return _courses;
    }

    // SETTERS
    public void set_tel(String tel) {
        _tel = tel;
    }
    public void setCourses(List<Course> courses) {
        _courses.addAll(courses);
    }

}
