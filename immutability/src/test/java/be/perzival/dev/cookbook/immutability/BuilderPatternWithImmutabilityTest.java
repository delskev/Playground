package be.perzival.dev.cookbook.immutability;

import be.perzival.dev.cookbook.immutability.dto.ImmutableUserDto;
import be.perzival.dev.cookbook.immutability.dto.ImmutableUserWithBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;

class BuilderPatternWithImmutabilityTest {

    @Test
    void simpleBuilder() {
        //Given
        //The advantage of Builder is that you can encapsulate validation etc...
        //The main disadvantages of it is the ton of boilerplate :(
        //Also here the ImmutableUserWithBuilder has a nested builder but it can be external as well
        //Nested builder has advantage that you can enforce builder usage by putting constructor private
        ImmutableUserWithBuilder immutableUserWithBuilder = ImmutableUserWithBuilder.builder()
                .withFirstName("firstName")
                .withLastName("lastName")
                .withBirthDate(LocalDate.now())
                .addLuckyNumber(42)
                .addLuckyNumber(404)
                .addLuckyNumber(500)
                .build();
        //Then
        Assertions.assertEquals("firstName", immutableUserWithBuilder.getFirstName());
        Assertions.assertEquals("lastName", immutableUserWithBuilder.getLastName());
        Assertions.assertEquals(LocalDate.now(), immutableUserWithBuilder.getBirthDate());
        Assertions.assertEquals(3, immutableUserWithBuilder.getLuckyNumberList().size());
        //Then
    }

    @Test
    void builderWithFramework() {
        //let's generate a builder to get rid of boilerplate and also give us more functionality in data control
        ImmutableUserDto immutableUserDto = ImmutableUserDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .build();
        //Then
        Assertions.assertEquals("firstName", immutableUserDto.getFirstName());
        Assertions.assertEquals("lastName", immutableUserDto.getLastName());
        Assertions.assertEquals(LocalDate.now(), immutableUserDto.getBirthDate());
        Assertions.assertEquals(Collections.emptyList(), immutableUserDto.getLuckyNumberList());
        Assertions.assertEquals(LocalDate.now(), immutableUserDto.getBirthDate());

        //We can also use constructor
        ImmutableUserDto immutableUserDto2 = ImmutableUserDto.of("firstName", "lastName");
        //Then
        Assertions.assertEquals("firstName", immutableUserDto2.getFirstName());
        Assertions.assertEquals("lastName", immutableUserDto2.getLastName());
        Assertions.assertEquals(LocalDate.now(), immutableUserDto2.getBirthDate());
        Assertions.assertEquals(Collections.emptyList(), immutableUserDto2.getLuckyNumberList());
        Assertions.assertEquals(LocalDate.now(), immutableUserDto2.getBirthDate());
    }
}
