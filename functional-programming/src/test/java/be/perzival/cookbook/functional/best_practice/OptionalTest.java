package be.perzival.cookbook.functional.best_practice;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.OptionalLong;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptionalTest {
    private static final String STARS = "***";

    //Do Not Declare Any Field of Type Optional
    //Do Not Use Optional in Constructors Arguments
    //Do Not Use Optional in Setters Arguments
    //Do Not Use Optional in Methods Arguments
    //Do Not Use Optional to Return Empty Collections or Arrays
    //Avoid Using Optional in Collections
    //Reject Wrapped Values Based on a Predefined Rule Using filter()

    @Test
    public void noNullToOptional() {
        //Never Assign Null to an Optional Variable
        Assertions.assertThrows(NullPointerException.class, () -> Optional.of(null));//This is bad
    }

    @Test
    public void diffBetweenOfAndOfNullable() {
        //Do Not Confuse Optional.of() and Optional.ofNullable()
        Assertions.assertThrows(NullPointerException.class, () -> Optional.of(null));//This is bad
        Assertions.assertDoesNotThrow(() -> Optional.ofNullable(null));//This is bad
    }

    @Test
    public void avoidGenericOptionalUsage() {
        //Avoid Optional<T> and Choose Non-Generic OptionalInt, OptionalLong, or OptionalDouble
        // AVOID
        Optional<Integer> priceInt = Optional.of(50);
        Optional<Long> priceLong = Optional.of(50L);
        Optional<Double> priceDouble = Optional.of(50.43d);

        // PREFER for better performance
        OptionalInt priceInteger2 = OptionalInt.of(50);           // unwrap via getAsInt()
        OptionalLong priceLong2 = OptionalLong.of(50L);        // unwrap via getAsLong()
        OptionalDouble priceDouble2 = OptionalDouble.of(50.43d); // unwrap via getAsDouble()
    }

    @Test
    public void equalsOptional() {
        //There Is No Need to Unwrap Optionals for Asserting Equality
        //Given
        Optional<String> actualItem = Optional.of("Shoes");
        Optional<String> expectedItem = Optional.of("Shoes");
        //When
        //Then
        Assertions.assertEquals(expectedItem, actualItem);
    }

    @Test
    public void badCheckOfOptional() {
        //Given
        //When
        Optional<String> output = doSomething();
        //Then
        if (output.isPresent()) {
            Assertions.assertEquals("**Hello World**", addStars(output.get()));
        }
    }

    @Test
    public void worstThingToDoWithOptional() {
        //Given
        //When
        Optional<String> output = doSomething();
        String result = output.orElse(null);//NEVER EVER do this
        //If you need a null, something's wrong with you design re-think it
        //Then
    }

    @Test
    public void goodCheckOfOptional() {
        //Given
        //When
        String output = doSomething()
                .map(OptionalTest::addStars)
                .orElse("N/A");
        //Then
        Assertions.assertEquals("**Hello World**", output);

        String testValue = null;
        String output2 = Optional.ofNullable(testValue)
                .map(OptionalTest::addStars)
                .orElse("N/A");
        Assertions.assertEquals("N/A", output2);

        String val = "test";
        val = null;
        Optional<String> option = Optional.ofNullable(val)
                .map(OptionalTest::addStars);
        Assertions.assertFalse(option.isPresent());
    }

    @Test
    public void differenceBetweenMapAndFlatMap() {
        //Transform Values Via Map() and flatMap()
        List<List<Integer>> lists = generateListOfList();
        System.out.println(lists);
        //Calculate square of each number

        //map
        lists.stream()
                .collect(ArrayList::new, List::addAll, List::addAll)
                .stream()
                .map(elem -> elem + "² = " + Math.pow((int)elem, 2))
                .forEach(elem -> System.out.println(elem));

        //flatMap
        lists.stream()
                .flatMap(List::stream)
                .map(elem -> elem + "² = " + Math.pow((int)elem, 2))
                .forEach(elem -> System.out.println(elem));
    }

    @Test
    public void ifPresentExample() {
        //Given
        Optional<String> output = doSomething();
        Optional<String> empty = Optional.empty();
        //When
        //Consume an Optional if it Is Present. Do Nothing if it Is Not Present. This Is a Job For Optional.ifPresent().
        output.ifPresent(System.out::println);
        //Consume an Optional if it Is Present. If it Is Not Present, Then Execute an Empty-Based Action.
        // This Is a Job For Optional.ifPresentElse(), Java 9.
        empty.ifPresentOrElse(System.out::println, () -> System.out.println("String not found"));
    }

    @Test
    public void DifferenceBetweenOrElseAndOrElseGet() {
        //When No Value Is Present, Set/Return an Already-Constructed Default Object Via the Optional.orElse() Method
        //When No Value Is Present, Set/Return a Non-Existent Default Object Via the Optional.orElseGet() Method
        //Given
        Optional<String> stringOptional = Optional.ofNullable(null);//put Null for purpose
        //When
        String string_is_empty = stringOptional.orElse("String is Empty");
        //orElseGet will be use when a process is required in case of empty
        //i.e: when there's a failure in a service and we need to fire an event to a queue
        String string_or_default = stringOptional.orElseGet(this::defaultString);
        //Then
        Assertions.assertEquals("String is Empty", string_is_empty);
        Assertions.assertEquals("N/A", string_or_default);

        //Worst case
        AtomicInteger atomicInteger = new AtomicInteger(0);
        stringOptional = Optional.ofNullable("Hello world");
        String worst_case = stringOptional.orElse(increment(atomicInteger));
        Assertions.assertEquals("Hello world", worst_case);//the result is the good one
        Assertions.assertEquals(1, atomicInteger.intValue());//But here the method has been called, which is bad...
    }

    @Test
    public void DifferenceBetweenOrElseAndOr() {
        //When No Value Is Present, Set/Return an Already-Constructed Default Object Via the Optional.orElse() Method
        //When No Value Is Present, Set/Return a Non-Existent Default Object Via the Optional.orElseGet() Method
        //Given
        Optional<String> stringOptional = Optional.ofNullable(null);//put Null for purpose
        //When
        String string_is_empty = stringOptional.orElse("String is Empty");
        //orElseGet will be use when a process is required in case of empty
        //i.e: when there's a failure in a service and we need to fire an event to a queue
        Optional<String> string_or_default = stringOptional.or(() -> Optional.of("N/A"));
        //Then
        Assertions.assertEquals("String is Empty", string_is_empty);
        Assertions.assertEquals("N/A", string_or_default.get());

        //Worst case
        AtomicInteger atomicInteger = new AtomicInteger(0);
        stringOptional = Optional.ofNullable("Hello world");
        String worst_case = stringOptional.orElse(increment(atomicInteger));
        Assertions.assertEquals("Hello world", worst_case);//the result is the good one
        Assertions.assertEquals(1, atomicInteger.intValue());//But here the method has been called, which is bad...
    }

    @Test
    public void manageException() {
        //When No Value Is Present, Throw a java.util.NoSuchElementException(or an Explicit Exception)
        //Exception Via orElseThrow() Since Java 10
        //Given
        Optional<String> stringOptional = Optional.ofNullable(null);//put Null for purpose
        //When
        //Will throw and exception because of Empty value -> Better than an unexpected NullPointerException
        Assertions.assertThrows(NoSuchElementException.class, () -> stringOptional.orElseThrow(NoSuchElementException::new));
    }

    public String defaultString() {
        return "N/A";
    }

    public String increment(AtomicInteger atomicInteger) {
        atomicInteger.incrementAndGet();
        return "N/A";
    }

    private List<List<Integer>> generateListOfList(){
        return IntStream.rangeClosed(1, 10)
                .boxed()
                .map(n1 ->
                        IntStream.rangeClosed(1, 10)
                        .boxed()
                                .map(n2 -> n1 * n2)
                                .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    private final Optional<String> doSomething() {
        //do stuff
        return Optional.of("Hello World");
    }

    static final String addStars(String text) {
        return "**".concat(text).concat("**");
    }
}
