package be.civadis.gpdoc.solid.srp;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;


/**
 * This is a bad case of SRP ( Single repository principle )
 */
public class User {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public User(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public void setFirstName(String firstName) {
        if(StringUtils.isBlank(firstName)) {
            throw new IllegalArgumentException("first Name is mandatory");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(StringUtils.isBlank(firstName)) {
            throw new IllegalArgumentException("first Name is mandatory");
        }
        this.lastName = lastName;
    }

    public void setPhoneNumber(String phoneNumber) {
        if(StringUtils.isBlank(firstName)) {
            throw new IllegalArgumentException("first Name is mandatory");
        }
        this.phoneNumber = phoneNumber;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        if(StringUtils.isBlank(firstName)) {
            throw new IllegalArgumentException("first Name is mandatory");
        }
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

}
