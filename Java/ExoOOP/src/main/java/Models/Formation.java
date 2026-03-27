package Models;

import java.util.List;

public class Formation {
    private Long _id;
    private final String _name;
    private String _description;
    private final List<Course> _courses;
    private List<Course> _prerequisiteCourses;
    private List<Trainer> _trainers;
    private final Trainer _mainTrainer;
    private Room _classroom;

    public Formation(String name, List<Course> courses, Trainer mainTrainer) {
        _name = name;
        _courses = courses;
        _mainTrainer = mainTrainer;
        _courses.forEach(course ->  _trainers.add(course.getTrainer()));
    }

    // GETTERS
    public Long get_id() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public String getDescription() {
        return _description;
    }

    public List<Course> getCourses() {
        return _courses;
    }

    public List<Course> getPrerequisiteCourses() {
        return _prerequisiteCourses;
    }

    public List<Trainer> getTrainers() {
        return _trainers;
    }

    public Trainer getMainTrainer() {
        return _mainTrainer;
    }

    public Room getLocal() {
        return _classroom;
    }

    // SETTERS
    public void set_id(Long _id) {
        this._id = _id;
    }
    public void setDescription(String description) {
        _description = description;
    }
    public void setLocal(Room classroom) {
        _classroom = classroom;
    }

    public void addCourses(List<Course> courses) {
        _courses.addAll(courses);
    }
    public void addPrerequisiteCourses(List<Course> courses) {
        _prerequisiteCourses.addAll(courses);
    }

}
