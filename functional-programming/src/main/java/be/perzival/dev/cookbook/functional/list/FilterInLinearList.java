package be.perzival.dev.cookbook.functional.list;

import be.perzival.dev.cookbook.functional.utils.Configuration;
import be.perzival.dev.cookbook.functional.utils.DataUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@State(Scope.Benchmark)
public class FilterInLinearList {
    private final List<Integer> TEST_DATA;

    public FilterInLinearList() {
        this.TEST_DATA = DataUtils.generateDataIntLinearList(10000);
    }

    @Benchmark
    public void filterEvenNumberProc() {
        List<Integer> evenNumberList = new ArrayList<>();
        for (int elem : TEST_DATA) {
            if( elem % 2 ==0) {
                evenNumberList.add(elem);
            }
        }
    }

    @Benchmark
    public void filterEvenNumberFunc() {
        TEST_DATA.stream()
                .filter(number -> number % 2 ==0)
                .collect(Collectors.toList());
    }

    /*
     * ============================== HOW TO RUN THIS TEST: ====================================
     *
     * You are expected to see the run with large number of iterations, and
     * very large throughput numbers. You can see that as the estimate of the
     * harness overheads per method call. In most of our measurements, it is
     * down to several cycles per call.
     *
     * a) Via command-line:
     *    $ mvn clean install
     *    $ java -jar target/benchmarks.jar JMHSample_01
     *
     * JMH generates self-contained JARs, bundling JMH together with it.
     * The runtime options for the JMH are available with "-h":
     *    $ java -jar target/benchmarks.jar -h
     *
     * b) Via the Java API:
     *    (see the JMH homepage for possible caveats when running from IDE:
     *      http://openjdk.java.net/projects/code-tools/jmh/)
     */

    public static void main(String[] args) throws RunnerException {
        new Runner(Configuration.defaultBenchmarkOption()).run();

    }
}
