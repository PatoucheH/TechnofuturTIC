package com.entities;

import java.time.LocalDate;
import java.util.Objects;


public class Author {
    private Integer id;
    private String firstname;
    private String lastname;
    private LocalDate birthdate;

    public Author(){}
    public Author(String firstname, String lastname, LocalDate birthdate) {
        this();
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthdate = birthdate;
    }
    public Author(Integer id, String firstname, String lastname, LocalDate birthdate) {
        this(firstname, lastname, birthdate);
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(getId(), author.getId()) && Objects.equals(getFirstname(), author.getFirstname()) && Objects.equals(getLastname(), author.getLastname()) && Objects.equals(getBirthdate(), author.getBirthdate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getBirthdate());
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", birthdate=" + birthdate +
                '}';
    }
}
