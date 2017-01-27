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
public class LongArithmeticMean implements Collector<Long, LongArithmeticMean.Temp, Double> {

    public class Temp {
        long sum = 0;
        long count = 0;
    }

    @Override
    public Supplier<Temp> supplier() {
        return Temp::new;
    }

    @Override
    public BiConsumer<Temp, Long> accumulator() {
        return (temp, value) -> {
            temp.count++;
            temp.sum += value;
        };
    }

    @Override
    public BinaryOperator<Temp> combiner() {
        return (temp1, temp2) -> {
            temp1.sum += temp2.sum;
            temp1.count += temp2.count;
            return temp1;
        };
    }

    @Override
    public Function<Temp, Double> finisher() {
        return temp -> (double) temp.sum / temp.count;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.singleton(Characteristics.UNORDERED);
    }

}
