package be.perzival.dev.cookbook.immutability.dto;

import java.time.LocalDate;
import java.util.List;

public interface User {
    String getFirstName();
    String getLastName();
    List<Integer> getLuckyNumberList();
    LocalDate getBirthDate();
}
