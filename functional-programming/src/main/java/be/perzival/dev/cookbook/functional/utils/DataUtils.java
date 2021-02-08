package be.perzival.dev.cookbook.functional.utils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public interface DataUtils {

    static List<Integer> generateDataIntLinearList(int amount) {
        return IntStream.rangeClosed(1, amount)
                .boxed()
                .collect(Collectors.toList());
    }

    static List<Integer> generateDataIntRandomList(int amount) {
        return new Random()
                .ints(0, 10000)
                .limit(amount)
                .boxed().collect(Collectors.toList());
    }
}
