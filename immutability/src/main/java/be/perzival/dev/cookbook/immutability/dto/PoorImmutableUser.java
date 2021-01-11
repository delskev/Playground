package be.perzival.dev.cookbook.immutability.dto;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class PoorImmutableUser implements User {
    private final String firstName; // String is immutable
    private final String lastName;
    private final List<Integer> luckyNumberList;
    private final LocalDate birthDate; //LocalDate is immutable

    public PoorImmutableUser() {
        this("N/A", "N/A", Collections.emptyList(), LocalDate.now());
    }

    public PoorImmutableUser(String firstName, String lastName, List<Integer> luckyNumberList, LocalDate birthDate) {
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
        //Be careful here never return a list that could be modified
        return Collections.unmodifiableList(luckyNumberList);
    }

    @Override
    public LocalDate getBirthDate() {
        return this.birthDate;
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
