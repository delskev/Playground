package be.perzival.dev.cookbook.immutability.dto;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BetterImmutableUser implements User {
    private final String firstName; // String is immutable
    private final String lastName;
    private final List<Integer> luckyNumberList;
    private final LocalDate birthDate; //LocalDate is immutable

    public BetterImmutableUser() {
        this("N/A", "N/A", Collections.emptyList(), LocalDate.now());
    }

    public BetterImmutableUser(String firstName, String lastName, List<Integer> luckyNumberList, LocalDate birthDate) {
        //This is a dumb null check, better use validation(good for mandatory and nullable fields) and Builder
        this.firstName = StringUtils.isEmpty(firstName) ? "N/A" : firstName ;
        this.lastName = StringUtils.isEmpty(lastName) ? "N/A" : lastName;
        this.luckyNumberList = luckyNumberList == null ? Collections.emptyList() : luckyNumberList;
        this.birthDate = birthDate == null ? LocalDate.now() : birthDate;
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

    public BetterImmutableUser addLuckyNumber(Integer luckyNumber) {
        List<Integer> newList = new ArrayList<>();
        //Don't use clone() to do that, this could be a disaster !!
        newList.addAll(luckyNumberList);
        newList.add(luckyNumber);

        return new BetterImmutableUser(
                this.getFirstName(),
                this.getLastName(),
                newList,
                this.birthDate);
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
