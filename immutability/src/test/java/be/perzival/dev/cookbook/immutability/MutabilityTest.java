package be.perzival.dev.cookbook.immutability;

import be.perzival.dev.cookbook.immutability.dto.MutableUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

class MutabilityTest {
    //NPE => Null Pointer exceptions

    @Test
    void simpleMutabilityTest() {
        //Given
        MutableUser mutableUser = new MutableUser("");
        //When
        mutableUser.setFirstName("firstName");
        //Then
        Assertions.assertEquals("firstName", mutableUser.getFirstName());
        //The problem here is all the unmanageable null values
        Assertions.assertEquals(null, mutableUser.getLastName());
        Assertions.assertEquals(null, mutableUser.getBirthDate());
        Assertions.assertEquals(null, mutableUser.getLuckyNumberList());
        //Which could result in a null pointerException or unsafe state
        Assertions.assertThrows(NullPointerException.class, () -> mutableUser.getLuckyNumberList().size());
    }

    @Test
    void avoidNullMutabilityTest() {
        //Given
        //This is the easiest solution to avoid NPE
        MutableUser defaultMutableUser = new MutableUser("firstName",
                "lastName",
                new ArrayList(),
                LocalDate.now());
        defaultMutableUser.setFirstName("firstName");
        //another solution is to implement a constructor that avoid null value
        MutableUser mutableUserWithoutNull = new MutableUser();
        //Here no more NPE
        Assertions.assertEquals("firstName", defaultMutableUser.getFirstName());
        Assertions.assertEquals("lastName", defaultMutableUser.getLastName());
        Assertions.assertEquals(LocalDate.now(), defaultMutableUser.getBirthDate());
        Assertions.assertEquals(Collections.emptyList(), defaultMutableUser.getLuckyNumberList());
        Assertions.assertEquals(0, defaultMutableUser.getLuckyNumberList().size());
        //Also here
        Assertions.assertEquals("N/A", mutableUserWithoutNull.getFirstName());
        Assertions.assertEquals("N/A", mutableUserWithoutNull.getLastName());
        Assertions.assertEquals(LocalDate.now(), mutableUserWithoutNull.getBirthDate());
        Assertions.assertEquals(Collections.emptyList(), mutableUserWithoutNull.getLuckyNumberList());
        Assertions.assertEquals(0, mutableUserWithoutNull.getLuckyNumberList().size());
    }
}
