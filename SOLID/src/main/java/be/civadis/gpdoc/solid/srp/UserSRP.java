package be.civadis.gpdoc.solid.srp;

import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;


/**
 * This is a bad case of SRP ( Single repository principle )
 */

public class UserSRP {
    private final String firstName;
    private final String lastName;
    private final String phoneNumber;
    private final LocalDate dateOfBirth;

    public UserSRP(String firstName, String lastName, String phoneNumber, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
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

    public static UserSRP.Builder builder(){
        return new UserSRP.Builder();
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String phoneNumber;
        private LocalDate dateOfBirth;

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }
        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }
        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }
        public Builder withDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public UserSRP build(){
            if(this.firstName == null || StringUtils.isBlank(this.firstName)) {
                throw new IllegalArgumentException("first name mandatory");
            }
            if(this.lastName == null || StringUtils.isBlank(this.lastName)) {
                throw new IllegalArgumentException("last name mandatory");
            }
            if(this.phoneNumber == null || StringUtils.isBlank(this.phoneNumber)) {
                throw new IllegalArgumentException("phone number mandatory");
            }
            if(this.dateOfBirth == null) {
                throw new IllegalArgumentException("date of birth mandatory");
            }

            return new UserSRP(firstName, lastName, phoneNumber, dateOfBirth);
        }
    }
}
