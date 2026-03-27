package Models;

public class Person {
    private final String _firstName;
    private final String _name;
    private String _email;

    public Person(String firstName, String name, String email) {
        _firstName = firstName;
        _name = name;
        _email = email;
    }

    // GETTERS
    public String getFirstName() {
        return _firstName;
    }
    public String getName() {
        return _name;
    }
    public String getEmail() {
        return _email;
    }


    public void changeEmail(String email) {
        _email = email;
    }
}
