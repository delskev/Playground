package be.perzival.dev.playground.flogger;

import com.google.common.flogger.FluentLogger;
import com.google.common.flogger.StackSize;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static com.google.common.flogger.LazyArgs.lazy;
import static org.awaitility.Awaitility.await;

class FloggerGettingStarted {
    private static final FluentLogger logger = FluentLogger.forEnclosingClass();
    //put arg: -Djava.util.logging.config.file=./src/test/resources/logging.properties


    //The system backend uses JDK logger,
    //so just set the log level of the equivalently named JDK logger or parent logger.
    //Using a logging.properties file is best.
    @Test
    void sayHelloInFlogger() {
        //SEVERE (highest value)
        //WARNING
        //INFO
        //CONFIG
        //FINE
        //FINER
        //FINEST (lowest value)

        logger.atSevere().log("Hello world !");
        logger.atWarning().log("Hello world !");
        logger.atInfo().log("Hello world !");
        logger.atConfig().log("Hello world !");
        logger.atFine().log("Hello world !");
        logger.atFiner().log("Hello world !");
        logger.atFinest().log("Hello world !");
    }

    @Test
    void iterativeLogTest() {
        for (int i = 0; i < 100; ++i) {
            logger.atInfo().every(10).log("log at iteration: %d", i);
        }
    }

    @ParameterizedTest()
    @EnumSource(StackSize.class)
    void exceptionLog(StackSize stackSize) {
        try {
            throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            logger.atInfo().log("before");
            logger.atInfo().withStackTrace(stackSize).withCause(e).log("Bad call"); //wrap exception within the log
            logger.atInfo().log("after");
        }
    }

    @Test
    void logWithTimeConstraint() {
        await().atMost(5, TimeUnit.SECONDS).until(() -> {
            logger.atInfo().atMostEvery(1, TimeUnit.SECONDS).log("Time log");
            return false;
        });
    }

    @Test
    void logVarArgs() {
        String[] args = new String[]{"Hello", "world", "from", "flogger"};
        logger.atInfo().logVarargs("Log varargs %s %s %s %s", args);
    }

    @Test
    void lazyLog() {
        AtomicInteger lazyCalled = new AtomicInteger();
        AtomicInteger eagerlyCalled = new AtomicInteger();
        await().atMost(5, TimeUnit.SECONDS).until(() -> {
            logger.atInfo()
                    .atMostEvery(1, TimeUnit.SECONDS)
                    .log("Lazy Value: %d != %d",
                            eagerlyCalled.incrementAndGet(),
                            lazy( () -> lazyCalled.incrementAndGet()));
            return false;
        });
    }
}
