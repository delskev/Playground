package be.perzival.dev.cookbook.immutability;

import be.perzival.dev.cookbook.immutability.dto.BetterImmutableUser;
import be.perzival.dev.cookbook.immutability.dto.MutableUser;
import be.perzival.dev.cookbook.immutability.dto.PoorImmutableUser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SimpleImmutabilityTest {
    //With immutability we have many advantages like:
    // - Thread safety
    // - Atomicity of failure
    // - Absence of hidden side-effects
    // - Protection against null reference errors
    // - Ease of caching
    // - Prevention of identity mutation
    // - Avoidance of temporal coupling between methods
    // - Support for referential transparency
    // - Protection from instantiating logically-invalid objects
    // - Protection from inadvertent corruption of existing objects

    @Test
    void immutabilityMadeItEasy() {
        //Here no NPE
        PoorImmutableUser user = new PoorImmutableUser("firstName", "lastName", new ArrayList<>(), LocalDate.now());
        Assertions.assertEquals("firstName", user.getFirstName());
        Assertions.assertEquals("lastName", user.getLastName());
        Assertions.assertEquals(LocalDate.now(), user.getBirthDate());
        Assertions.assertEquals(Collections.emptyList(), user.getLuckyNumberList());
        Assertions.assertEquals(0, user.getLuckyNumberList().size());
        //Also here
        PoorImmutableUser defaultUser = new PoorImmutableUser();// this is pretty useless that's why default constructor should be private
        Assertions.assertEquals("N/A", defaultUser.getFirstName());
        Assertions.assertEquals("N/A", defaultUser.getLastName());
        Assertions.assertEquals(LocalDate.now(), defaultUser.getBirthDate());
        Assertions.assertEquals(Collections.emptyList(), defaultUser.getLuckyNumberList());
        Assertions.assertEquals(0, defaultUser.getLuckyNumberList().size());
        //But here !!
        PoorImmutableUser userNullable = new PoorImmutableUser("firstName", "lastName", null, LocalDate.now());
        Assertions.assertEquals("firstName", userNullable.getFirstName());
        Assertions.assertEquals("lastName", userNullable.getLastName());
        Assertions.assertEquals(LocalDate.now(), userNullable.getBirthDate());
        //We have immutability but still get NPE !
        Assertions.assertThrows(NullPointerException.class, () -> userNullable.getLuckyNumberList().size());
        // This is because we poorly implement immutability
        // But here we don't have any null
        BetterImmutableUser userNoNull = new BetterImmutableUser(null, null, null, null);
        Assertions.assertEquals("N/A", userNoNull.getFirstName());
        Assertions.assertEquals("N/A", userNoNull.getLastName());
        Assertions.assertEquals(LocalDate.now(), userNoNull.getBirthDate());
        Assertions.assertEquals(Collections.emptyList(), userNoNull.getLuckyNumberList());
        Assertions.assertEquals(0, userNoNull.getLuckyNumberList().size());
    }

    @Test
    void avoidSideEffectWithImmutability() {
        //Given
        List<Integer> integerList = new ArrayList<>();
        MutableUser mutableUser = new MutableUser("", "", new ArrayList<>(), LocalDate.now());
        //When
        mutableUser.setLuckyNumberList(integerList);
        integerList.add(42);
        integerList.add(404);
        integerList.add(403);
        integerList.add(500);
        //Then
        //that's a big issue
        //here we have side effects and we broke the object state. we have no guarantee of what does the object contains
        Assertions.assertNotEquals(0, mutableUser.getLuckyNumberList().size());
        List<Integer> luckyNumberList = mutableUser.getLuckyNumberList();
        luckyNumberList.clear();
        //Another example of a major issue
        Assertions.assertEquals(0, mutableUser.getLuckyNumberList().size());

        //With immutability
        BetterImmutableUser immutableUser = new BetterImmutableUser("", "", new ArrayList<>(), LocalDate.now());
        //When
        //Can't set the list -> immutableUser.setLuckyNumberList(integerList);
        //So here we have no element
        Assertions.assertEquals(0, immutableUser.getLuckyNumberList().size());
        List<Integer> immutableLuckyNUmberList = immutableUser.getLuckyNumberList();
        //The modification of the list throw an exception. because we return an UnmodifiableList
        Assertions.assertThrows(UnsupportedOperationException.class, () -> immutableLuckyNUmberList.add(42));
    }

    @Test
    void dealWithList() {
        //How to add in a list with Immutable Object ?
        //Like we do with the most used object in Java -> String
        String example = "Hello";
        String example2 = example.concat(", world"); //example2 = "Hello, world!"
        //given
        BetterImmutableUser immutableUser = new BetterImmutableUser("", "", new ArrayList<>(), LocalDate.now());
        Assertions.assertEquals(0, immutableUser.getLuckyNumberList().size());
        BetterImmutableUser immutableUser2 = immutableUser.addLuckyNumber(42);
        Assertions.assertEquals(1, immutableUser2.getLuckyNumberList().size());
    }
}
