package be.perzival.dev.cookbook.functional.utils;

import be.perzival.dev.cookbook.functional.list.FilterInRandomList;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public interface Configuration {

    static Options defaultBenchmarkOption() {
        return new OptionsBuilder()
                .include(FilterInRandomList.class.getSimpleName())
                .warmupIterations(2)
                .measurementIterations(5)
                .timeUnit(TimeUnit.MILLISECONDS)
                .mode(Mode.AverageTime)
                .forks(1)
                .shouldDoGC(true)
                .build();
    }

    static Options allBenchmarkOption() {
        return new OptionsBuilder()
                .include(".*")
                .warmupIterations(2)
                .measurementIterations(5)
                .timeUnit(TimeUnit.MILLISECONDS)
                .mode(Mode.AverageTime)
                .forks(1)
                .shouldDoGC(true)
                .build();
    }
}
