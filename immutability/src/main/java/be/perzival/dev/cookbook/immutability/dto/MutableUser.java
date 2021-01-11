package be.perzival.dev.cookbook.immutability.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class MutableUser implements User {
    private String firstName;
    private String lastName;
    private List<Integer> luckyNumberList;
    private LocalDate birthDate;

    public MutableUser(String firstName) {
        //Chaining COnstructor is a best practice, write once use multiple times
        this(firstName, null, null, null);
    }

    public MutableUser() {
        this("N/A",
                "N/A",
                Collections.emptyList()/*Best practice to init an empty list*/,
                LocalDate.now());
    }

    public MutableUser(String firstName, String lastName, List<Integer> luckyNumberList, LocalDate birthDate) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.luckyNumberList = luckyNumberList;
        this.birthDate = birthDate;
    }

    @Override
    public String getFirstName() {
        return this.firstName;
    }

    @Override
    public String getLastName() {
        return this.lastName;
    }

    @Override
    public List<Integer> getLuckyNumberList() {
        return this.luckyNumberList;
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setLuckyNumberList(List<Integer> luckyNumberList) {
        this.luckyNumberList = luckyNumberList;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "MutableUser{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", luckyNumberList=" + luckyNumberList +
                ", birthDate=" + birthDate +
                '}';
    }
}
