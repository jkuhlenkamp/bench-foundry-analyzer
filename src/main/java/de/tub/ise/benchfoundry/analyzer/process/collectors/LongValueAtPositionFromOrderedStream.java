package de.tub.ise.benchfoundry.analyzer.process.collectors;

import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * @author Joern Kuhlenkamp (j.kuhlenkamp@gmail.com)
 *         Created by on 27.01.17.
 */
public class LongValueAtPositionFromOrderedStream implements Collector<Long, LongValueAtPositionFromOrderedStream.Counter, Long> {

    private final long position;

    class Counter {
        long count = 0L;
        long candidate = 0L;
    }

    public LongValueAtPositionFromOrderedStream(long position) {
        this.position = position;
    }

    @Override
    public Supplier<Counter> supplier() {
        return Counter::new;
    }

    @Override
    public BiConsumer<Counter, Long> accumulator() {
        return (counter, candidate) -> {
            if ((counter.count+1) == position)
                counter.candidate = candidate;
            counter.count++;
        };
    }

    @Override
    public BinaryOperator<Counter> combiner() {
        return (counter, counter2) -> counter;
    }

    @Override
    public Function<Counter, Long> finisher() {
        return counter -> counter.candidate;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.emptySet();
    }

}
