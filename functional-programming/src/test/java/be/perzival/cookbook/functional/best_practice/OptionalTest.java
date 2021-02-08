package be.perzival.cookbook.functional.best_practice;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class OptionalTest {
    private static final String STARS = "***";

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
        Assertions.assertEquals("N/A", output);

        String val = "test";
        val = null;
        Optional<String> option = Optional.ofNullable(val)
                .map(OptionalTest::addStars);
        Assertions.assertTrue(option.isPresent());
        Assertions.assertEquals("**test**", option.get());
    }

    @Test
    public void differenceBetweenMapAndFlatMap() {
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
